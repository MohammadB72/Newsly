package app.newsly.core.network

import android.content.Context
import app.newsly.core.model.InAppError
import app.newsly.core.model.NetworkError
import app.newsly.core.model.RequestException
import app.newsly.core.model.RequestResult
import app.newsly.core.network.util.isNetworkConnected
import app.newsly.shared.resources.R
import com.google.gson.Gson
import kotlinx.coroutines.delay
import retrofit2.HttpException

private data class NetworkResponse<T>(
    val status: String,
    val data: T,
    val error: NetworkError
)

suspend fun <T : Any> apiCall(
    context: Context,
    block: suspend () -> T,
): RequestResult<T> {
    if (context.isNetworkConnected) {
        runCatching {
            if (BuildConfig.FLAVOR.equals("mock", ignoreCase = true)) {
                delay(2000)
            }
            block()
        }
            .onSuccess { value ->
                return RequestResult.Success(value)
            }.onFailure { exception ->
                return RequestResult.Fail(RequestException(networkError = exception.toNetworkError()))
            }
    } else {
        return RequestResult.Fail(RequestException(inAppError = InAppError(resMessage = R.string.not_connected)))
    }
    return RequestResult.Fail(RequestException(inAppError = InAppError(resMessage = R.string.network_unknown_error)))
}

private fun Throwable.toNetworkError(): NetworkError {
    return if (this is HttpException) {
        this.response()?.errorBody()?.string()?.let {
            Gson().fromJson(it, NetworkResponse::class.java).error
        } ?: run { NetworkError(message = this.message) }
    } else {
        NetworkError(message = this.message)
    }
}
