package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.core.model.domain.News
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
                        data.map { it.toDomainModel() }
                    ))
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }
}