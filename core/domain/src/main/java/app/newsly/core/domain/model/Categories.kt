package app.newsly.core.domain.model

data class Categories(
    val id: Int,
    val title: String,
    val postsCount: Int,
    val icon: String?,
)
