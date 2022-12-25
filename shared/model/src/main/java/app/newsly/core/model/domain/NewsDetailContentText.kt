package app.newsly.core.model.domain

data class NewsDetailContentText(
    override val hasValidData: Boolean,
    val text: String,
) : BaseDomainModel()