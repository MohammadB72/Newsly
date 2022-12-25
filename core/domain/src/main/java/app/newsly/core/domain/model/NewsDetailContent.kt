package app.newsly.core.domain.model

import app.newsly.core.network.model.ContentType

data class NewsDetailContent<T>(
    val type: ContentType?,
    val data: T?
)
