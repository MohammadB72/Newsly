package app.newsly.core.domain.model

import app.newsly.core.domain.model.enums.HeadlineType


data class ContentItemTextHeadline(
    val type: HeadlineType,
    val text: String,
) : ContentItem