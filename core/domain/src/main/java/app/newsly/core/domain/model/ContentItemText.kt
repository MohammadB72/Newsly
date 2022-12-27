package app.newsly.core.domain.model

data class ContentItemText(
    val text: String,
    var markups: List<Markup> = emptyList()
) : ContentItem