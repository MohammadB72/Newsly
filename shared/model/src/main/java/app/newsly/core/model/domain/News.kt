package app.newsly.core.model.domain

data class News(
    val id: Int,
    val title: String,
    val image: String,
    val author: String,
    val authorAvatar: String,
    val date: String,
    val link: String
) : BaseDomainModel()