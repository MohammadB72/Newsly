package app.newsly.core.model.network

import app.newsly.core.model.domain.Author
import app.newsly.core.model.domain.News
import app.newsly.core.model.extension.differenceWithToday
import app.newsly.core.model.extension.isNotNullAndBlank
import app.newsly.core.model.extension.toDate
import com.google.gson.annotations.SerializedName

data class NewsNetworkModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("author") val author: AuthorNetworkModel?,
    @SerializedName("date") val date: String?,
    @SerializedName("link") val link: String?
) : BaseNetworkModel() {
    override fun toDomainModel(): News =
        News(
            hasValidData = (id != null && title.isNotNullAndBlank() && image.isNotNullAndBlank()),
            id = id ?: -1,
            title = title ?: "",
            image = image ?: "",
            author = author?.toDomainModel() ?: Author.EMPTY,
            date = date?.toDate()?.differenceWithToday() ?: "",
            link = link ?: ""
        )
}