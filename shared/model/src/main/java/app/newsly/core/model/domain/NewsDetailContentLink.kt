package app.newsly.core.model.domain

data class NewsDetailContentLink(
    override val hasValidData: Boolean,
    val text: String,
    val url: String,
) : BaseDomainModel()