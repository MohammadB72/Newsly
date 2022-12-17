package app.newsly.core.domain

import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnError
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<P, R> {
    operator fun invoke(): Flow<RequestResult<R>> {
        return flow {
            execute()
                .doOnSuccess {
                    emit(RequestResult.Success(mapper(it)))
                }.doOnError {
                    emit(RequestResult.Error(it))
                }
        }
    }

    protected abstract suspend fun execute(): RequestResult<P>
    protected abstract fun mapper(apiModel: P): R
}