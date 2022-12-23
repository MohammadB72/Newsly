package app.newsly.core.model.network

import app.newsly.core.model.domain.News
import app.newsly.core.model.extension.differenceWithToday
import app.newsly.core.model.extension.toDate

import com.google.gson.annotations.SerializedName

data class NewsNetworkModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_avatar") val authorAvatar: String,
    @SerializedName("date") val date: String,
    @SerializedName("link") val link: String
) : BaseNetworkModel() {
    override fun toDomainModel(): News = News(
        id = id,
        title = title,
        image = image,
        author = author,
        authorAvatar = authorAvatar,
        date = date.toDate()?.differenceWithToday().toString(),
        link = link
    )
}