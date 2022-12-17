package app.newsly.core.network

import android.content.Context
import app.newsly.core.model.ErrorBehaviour
import app.newsly.core.model.RequestException
import app.newsly.core.model.RequestResult
import app.newsly.core.network.util.isNetworkConnected
import retrofit2.HttpException

suspend fun <T : Any> apiCall(
    context: Context,
    block: suspend () -> T,
): RequestResult<T> {
    if (context.isNetworkConnected) {
        runCatching { block() }
            .onSuccess { value ->
                return RequestResult.Success(value)
            }.onFailure { exception ->
                val message = when (exception) {
                    is HttpException -> {
                        when (exception.code()) {
                            404 -> {
                                "Not Found"
                            }
                            403 -> {
                                "Forbidden"
                            }
                            else -> {
                                exception.message
                            }
                        }
                    }
                    else -> {
                        exception.message
                    }
                }
                return RequestResult.Error(
                    RequestException(
                        message = message,
                        errorBehaviour = ErrorBehaviour.SHOW_SNACK,
                    )
                )
            }
    } else {
        return RequestResult.Error(
            RequestException(
                message = "No Internet Connection",
                errorBehaviour = ErrorBehaviour.SHOW_SNACK
            )
        )
    }
    return RequestResult.Error(
        RequestException(
            message = "Unknown exception from ApiCall",
            errorBehaviour = ErrorBehaviour.SHOW_SNACK
        )
    )
}
