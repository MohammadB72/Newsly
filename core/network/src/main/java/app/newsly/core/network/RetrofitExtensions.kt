package app.newsly.core.network

import app.newsly.core.model.Result


suspend fun <T> apiCallWithResult(
    block: suspend () -> T,
): Result<T> {
    runCatching { block() }
        .onSuccess { value ->
            return Result.Success(value)
        }.onFailure { exception ->
            return Result.Error(Exception(exception))
        }
    return Result.Error(IllegalStateException("Unknown exception from ApiCall"))
}
