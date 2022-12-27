package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailApiModel(
    @SerializedName("id") val apiId: Int?,
    @SerializedName("title") val apiTitle: String?,
    @SerializedName("image_url") val apiImageUrl: String?,
    @SerializedName("author") val apiAuthor: AuthorApiModel?,
    @SerializedName("date") val apiDate: String?,
    @SerializedName("link") val apiLink: String?,
    @SerializedName("content") val apiContentList: List<ContentItemApiModel>?,
)
