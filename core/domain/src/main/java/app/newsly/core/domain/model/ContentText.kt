package app.newsly.core.domain.model

data class ContentText(
    val text: String,
    var markups: List<Markup> = emptyList()
) : Content