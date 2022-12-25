package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailContentImageNetworkModel(
    @SerializedName("url") val url: String?
)
