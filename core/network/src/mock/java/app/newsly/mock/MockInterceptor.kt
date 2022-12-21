package app.newsly.mock

import android.content.Context
import app.newsly.ApiConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


class MockInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mockResponseString = getResponse(context, chain.request())
        val headerResponse = Gson().fromJson(mockResponseString, HeaderResponse::class.java)
        //Timber.d("intercept: mockResponseString = $mockResponseString")
        return if (mockResponseString.isNotEmpty()) {
            Response.Builder().apply {
                message("")
                protocol(Protocol.HTTP_1_1)
                request(chain.request())
                code(headerResponse.httpStatusCode)
                body(mockResponseString.toResponseBody("text/json".toMediaTypeOrNull()))
            }.build()
        } else {
            chain.proceed(chain.request())
        }
    }
}

fun getResponse(context: Context, request: Request): String {
    val endPoints = getEndPoint(request).split("/")
    return MockResponseLoader.getResponseString(context, request.method, endPoints)
}

fun getEndPoint(request: Request): String {
    val url = request.url.toString()
    val queryParameterIndex = url.indexOf("?")
    val startIndex = ApiConfig.BASE_URL.length
    return when (queryParameterIndex) {
        -1 -> url.substring(startIndex)
        else -> url.substring(startIndex, queryParameterIndex)
    }
}

data class HeaderResponse(
    val httpStatusCode: Int,
)
