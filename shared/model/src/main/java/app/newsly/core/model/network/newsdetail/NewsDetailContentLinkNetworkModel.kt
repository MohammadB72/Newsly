package app.newsly.core.model.network.newsdetail

import com.google.gson.annotations.SerializedName

data class NewsDetailContentLinkNetworkModel(
    @SerializedName("text") val text: String?,
    @SerializedName("url") val url: String?
)
