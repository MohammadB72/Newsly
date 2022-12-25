package app.newsly.core.model.domain

data class NewsDetailContentImage(
    override val hasValidData: Boolean,
    val url: String,
) : BaseDomainModel()