package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsNetworkModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("author") val author: AuthorNetworkModel?,
    @SerializedName("date") val date: String?,
    @SerializedName("link") val link: String?
)