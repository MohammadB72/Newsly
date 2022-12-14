package app.newsly.core.domain

import app.newsly.core.model.Result
import app.newsly.core.model.doOnError
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<P, R> {
    operator fun invoke(): Flow<Result<R>> {
        return flow {
            execute()
                .doOnSuccess {
                    emit(Result.Success(mapper(it)))
                }.doOnError {
                    emit(Result.Error(it))
                }
        }
    }

    protected abstract suspend fun execute(): Result<P>
    protected abstract fun mapper(apiModel: P): R
}