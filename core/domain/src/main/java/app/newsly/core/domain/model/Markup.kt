package app.newsly.core.domain.model

import app.newsly.core.domain.model.enums.MarkupType

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)