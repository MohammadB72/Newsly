package app.newsly.core.network

import android.content.Context
import app.newsly.core.model.ApiException
import app.newsly.core.model.ApiResult
import app.newsly.core.model.ErrorBehaviour
import app.newsly.core.network.util.isNetworkConnected
import retrofit2.HttpException

suspend fun <T : Any> apiCallWithResult(
    context: Context,
    apiCallBlock: suspend () -> T,
): ApiResult<T> {
    if (context.isNetworkConnected) {
        runCatching { apiCallBlock() }
            .onSuccess { value ->
                return ApiResult.Success(value)
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
                return ApiResult.Error(
                    ApiException(
                        message = message,
                        errorBehaviour = ErrorBehaviour.SHOW_SNACK,
                    )
                )
            }
    } else {
        return ApiResult.Error(
            ApiException(
                message = "No Internet Connection",
                errorBehaviour = ErrorBehaviour.SHOW_SNACK
            )
        )
    }
    return ApiResult.Error(
        ApiException(
            message = "Unknown exception from ApiCall",
            errorBehaviour = ErrorBehaviour.SHOW_SNACK
        )
    )
}
