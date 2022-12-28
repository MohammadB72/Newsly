package app.newsly.core.domain

import app.newsly.core.data.repository.news.NewsRepository
import app.newsly.core.domain.model.Categories
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<RequestResult<List<Categories>>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository.getCategories()
                .doOnLoading {
                    emit(RequestResult.Loading)
                }
                .doOnSuccess { categoriesListApiModel ->
                    emit(RequestResult.Success(
                        categoriesListApiModel.map {
                            Categories(
                                id = it.apiId ?: -1,
                                title = it.apiTitle ?: "",
                                postsCount = it.apiPostsCount ?: 0,
                                icon = it.apiIcon
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

