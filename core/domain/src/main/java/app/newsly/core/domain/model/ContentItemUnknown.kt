package app.newsly.core.domain.model

data class ContentItemUnknown(
    val type: String,
    val attr: String
) : ContentItem