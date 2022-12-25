package app.newsly.core.domain.model

data class News(
    val id: Int,
    val title: String,
    val image: String,
    val author: Author,
    val date: String,
    val link: String
)