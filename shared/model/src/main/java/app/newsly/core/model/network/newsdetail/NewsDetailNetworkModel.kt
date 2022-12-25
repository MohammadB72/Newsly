package app.newsly.core.model.network.newsdetail

import app.newsly.core.model.domain.BaseDomainModel
import app.newsly.core.model.domain.DomainModelList
import app.newsly.core.model.domain.NewsDetail
import app.newsly.core.model.domain.NewsDetailContent
import app.newsly.core.model.network.BaseNetworkModel
import com.google.gson.annotations.SerializedName

data class NewsDetailNetworkModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("content") val content: NetworkModelList<NewsDetailContentNetworkModel>?,
) : BaseNetworkModel() {
    override fun toDomainModel(): NewsDetail =
        NewsDetail(
            hasValidData = id != null && content != null && content.isNotEmpty(),
            id = id ?: -1,
            content = content?.toDomainModel() as DomainModelList<NewsDetailContent<BaseDomainModel>>
        )
}
