package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailNetworkModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("content") val content: List<NewsDetailContentNetworkModel>?,
)
