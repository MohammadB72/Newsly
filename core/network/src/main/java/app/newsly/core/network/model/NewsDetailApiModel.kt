package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailApiModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("content") val content: List<ContentApiModel>?,
)
