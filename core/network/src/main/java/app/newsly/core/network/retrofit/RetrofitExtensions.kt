package app.newsly.core.network.retrofit

import android.content.Context
import app.newsly.core.model.*
import app.newsly.core.network.util.isNetworkConnected
import app.newsly.shared.resources.BuildConfig
import app.newsly.shared.resources.R
import com.google.gson.Gson
import kotlinx.coroutines.delay
import retrofit2.HttpException


suspend fun <T> apiCall(
    context: Context,
    block: suspend () -> T,
): RequestResult<T> {
    if (context.isNetworkConnected) {
        runCatching {
            if (BuildConfig.FLAVOR.equals(BuildConfig.FLAVOR, ignoreCase = true)) {
                delay(2000)
            }
            block()
        }.onSuccess { value ->
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
