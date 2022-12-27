package app.newsly.core.domain.model


data class NewsDetail(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val author: Author,
    val date: String,
    val link: String,
    val contentList: List<ContentItem>,
)
