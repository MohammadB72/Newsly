package app.newsly.core.model


sealed class RequestResult<out R> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(
        val exception: RequestException
    ) : RequestResult<Nothing>()

    object Loading : RequestResult<Nothing>()
}


suspend fun <T> RequestResult<T>.doOnSuccess(action: suspend (T) -> Unit): RequestResult<T> {
    if (this is RequestResult.Success) {
        action(this.data)
    }
    return this
}

suspend fun <T> RequestResult<T>.doOnError(action: suspend (RequestException) -> Unit) = apply {
    if (this is RequestResult.Error) {
        action(this.exception)
    }
}