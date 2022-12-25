package app.newsly.core.model.network.newsdetail

import app.newsly.core.model.domain.NewsDetailContentText
import app.newsly.core.model.network.BaseNetworkModel
import com.google.gson.annotations.SerializedName

data class NewsDetailContentTextNetworkModel(
    @SerializedName("text") val text: String?
) : BaseNetworkModel() {
    override fun toDomainModel(): NewsDetailContentText =
        NewsDetailContentText(
            hasValidData = text != null,
            text = text ?: ""
        )
}
