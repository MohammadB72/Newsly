package app.newsly.core.domain

import app.newsly.core.data.repository.news.NewsRepository
import app.newsly.core.domain.model.Category
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSubCategoriesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(categoryId: Int): Flow<RequestResult<List<Category>>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository.getSubCategories(categoryId = categoryId)
                .doOnLoading {
                    emit(RequestResult.Loading)
                }
                .doOnSuccess { categoriesListApiModel ->
                    emit(RequestResult.Success(
                        categoriesListApiModel.map {
                            Category(
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

