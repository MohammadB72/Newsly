package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.domain.model.NewsDetail
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<RequestResult<NewsDetail>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository
                .getNewsDetail()
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { data ->
                    val q = data.content
                    val a = q
                    //emit(RequestResult.Success(q))
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }
}