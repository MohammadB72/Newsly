package app.newsly.core.domain.model


data class NewsDetail(
    val id: Int,
    val imageUrl: String,
    val content: List<Content>,
)
