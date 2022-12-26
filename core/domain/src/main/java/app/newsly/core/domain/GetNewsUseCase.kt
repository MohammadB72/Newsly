package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
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

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<RequestResult<List<News>>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository
                .getNews()
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { data ->
                    emit(RequestResult.Success(
                        data.map {
                            News(
                                id = it.id ?: -1,
                                title = it.title ?: "",
                                image = it.image ?: "",
                                author = Author(it.author?.name ?: "", it.author?.avatar ?: ""),
                                date = it.date?.toDate()?.differenceWithToday() ?: "",
                                link = it.link ?: ""
                            )
                        }
                    ))
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }
}