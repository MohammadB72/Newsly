package app.newsly.core.network.retrofit

import com.google.gson.annotations.SerializedName

data class NetworkError(
    @SerializedName("key") val key: String? = null,
    @SerializedName("message") val message: String?,
)