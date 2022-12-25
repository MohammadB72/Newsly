package app.newsly.core.model.domain

data class NewsDetailContentTextBold(
    override val hasValidData: Boolean,
    val text: String,
) : BaseDomainModel()