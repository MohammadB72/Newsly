package app.newsly.core.domain.model

data class NewsDetail(
    val id: Int,
    val content: List<NewsDetailContent<Any>>
)
