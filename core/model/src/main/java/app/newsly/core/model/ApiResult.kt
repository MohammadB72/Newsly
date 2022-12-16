package app.newsly.core.model


sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(
        val exception: ApiException
    ) : ApiResult<Nothing>()

    object Loading : ApiResult<Nothing>()
}


suspend fun <T> ApiResult<T>.doOnSuccess(action: suspend (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) {
        action(this.data)
    }
    return this
}

suspend fun <T> ApiResult<T>.doOnError(action: suspend (ApiException) -> Unit) = apply {
    if (this is ApiResult.Error) {
        action(this.exception)
    }
}