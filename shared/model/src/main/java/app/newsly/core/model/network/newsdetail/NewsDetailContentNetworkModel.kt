package app.newsly.core.model.network.newsdetail

import app.newsly.core.model.domain.*
import app.newsly.core.model.network.BaseNetworkModel
import app.newsly.shared.util.fromJson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class NewsDetailContentNetworkModel(
    @SerializedName("type") val type: ContentType?,
    @SerializedName("data") val data: JsonObject?
) : BaseNetworkModel() {
    fun getType() {

    }

    override fun toDomainModel(): NewsDetailContent<BaseDomainModel> {
        return when (type) {
            ContentType.TEXT -> {
                NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()?.fromJson<NewsDetailContentText>() as BaseDomainModel
                )
            }
            ContentType.IMAGE -> {
                NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()?.fromJson<NewsDetailContentImage>() as BaseDomainModel
                )
            }
            ContentType.LINK -> {
                NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()?.fromJson<NewsDetailContentLink>() as BaseDomainModel
                )
            }
            ContentType.BOLD -> {
                NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()
                        ?.fromJson<NewsDetailContentTextBold>() as BaseDomainModel
                )
            }
            null -> {
                NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = null
                )
            }
        }
    }
}