package app.newsly.core.model.domain

data class News(
    override val hasValidData: Boolean,
    val id: Int,
    val title: String,
    val image: String,
    val author: Author,
    val date: String,
    val link: String
) : BaseDomainModel()