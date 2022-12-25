package app.newsly.core.model.network.newsdetail

import com.google.gson.annotations.SerializedName

data class NewsDetailContentImageNetworkModel(
    @SerializedName("url") val url: String?
)
