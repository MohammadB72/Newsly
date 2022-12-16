package app.newsly.core.domain

import app.newsly.core.model.ApiResult
import app.newsly.core.model.doOnError
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<P, R> {
    operator fun invoke(): Flow<ApiResult<R>> {
        return flow {
            execute()
                .doOnSuccess {
                    emit(ApiResult.Success(mapper(it)))
                }.doOnError {
                    emit(ApiResult.Error(it))
                }
        }
    }

    protected abstract suspend fun execute(): ApiResult<P>
    protected abstract fun mapper(apiModel: P): R
}