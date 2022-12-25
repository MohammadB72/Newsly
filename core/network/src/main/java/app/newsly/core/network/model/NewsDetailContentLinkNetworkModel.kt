package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailContentLinkNetworkModel(
    @SerializedName("text") val text: String?,
    @SerializedName("url") val url: String?
)
