package app.newsly.core.domain

import app.newsly.core.data.repository.news.NewsRepository
import app.newsly.core.domain.model.Author
import app.newsly.core.domain.model.News
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.shared.util.differenceWithToday
import app.newsly.shared.util.toDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(categoryId: Int, page: Int): Flow<RequestResult<List<News>>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository.getNewsByCategory(categoryId = categoryId, page = page)
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { newsList ->
                    emit(RequestResult.Success(
                        newsList.map {
                            News(
                                id = it.apiId ?: -1,
                                title = it.apiTitle ?: "",
                                image = it.apiImageUrl ?: "",
                                author = Author(
                                    it.apiAuthor?.apiName ?: "",
                                    it.apiAuthor?.apiAvatar ?: ""
                                ),
                                date = it.apiDate?.toDate()?.differenceWithToday()
                                    ?: "",
                                link = it.apiLink ?: ""
                            )
                        }
                    ))
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception = exception))
                }
        }
    }
}

