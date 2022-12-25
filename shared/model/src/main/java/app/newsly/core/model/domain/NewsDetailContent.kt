package app.newsly.core.model.domain

import app.newsly.core.model.network.newsdetail.ContentType

data class NewsDetailContent<T : BaseDomainModel>(
    override val hasValidData: Boolean,
    val type: ContentType?,
    val data: T?
) : BaseDomainModel()
