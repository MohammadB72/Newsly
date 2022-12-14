package app.newsly.core.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

suspend fun <T> Result<T>.doOnSuccess(action: suspend (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(this.data)
    }
    return this
}

suspend fun <T> Result<T>.doOnError(action: suspend (Exception) -> Unit) = apply {
    if (this is Result.Error) {
        action(this.exception)
    }
}