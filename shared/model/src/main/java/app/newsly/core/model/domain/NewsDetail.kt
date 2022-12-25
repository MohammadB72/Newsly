package app.newsly.core.model.domain

data class NewsDetail(
    override val hasValidData: Boolean,
    val id: Int,
    val content: DomainModelList<NewsDetailContent<BaseDomainModel>>
) : BaseDomainModel()
