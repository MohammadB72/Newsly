package app.newsly.core.domain.model

data class Category(
    val id: Int,
    val title: String,
    val postsCount: Int,
    val icon: String?,
)
