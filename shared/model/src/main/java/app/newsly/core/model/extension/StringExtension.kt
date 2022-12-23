package app.newsly.core.model.extension

fun String?.isNotNullAndBlank() = this != null && this.isNotBlank()