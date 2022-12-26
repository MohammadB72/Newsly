package app.newsly.core.domain.model

import app.newsly.core.network.model.enums.ContentType

data class Content<T>(
    val type: ContentType?,
    val attr: T?
)
