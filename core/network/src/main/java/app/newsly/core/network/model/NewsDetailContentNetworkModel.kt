package app.newsly.core.network.model


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class NewsDetailContentNetworkModel(
    @SerializedName("type") val type: ContentType?,
    @SerializedName("data") val data: JsonObject?
) {

    /*fun toDomainModel(): app.newsly.core.domain.model.NewsDetailContent<app.newsly.core.domain.model.BaseDomainModel> {
        return when (type) {
            ContentType.TEXT -> {
                app.newsly.core.domain.model.NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()
                        ?.fromJson<app.newsly.core.domain.model.NewsDetailContentText>() as app.newsly.core.domain.model.BaseDomainModel
                )
            }
            ContentType.IMAGE -> {
                app.newsly.core.domain.model.NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()
                        ?.fromJson<app.newsly.core.domain.model.NewsDetailContentImage>() as app.newsly.core.domain.model.BaseDomainModel
                )
            }
            ContentType.LINK -> {
                app.newsly.core.domain.model.NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()
                        ?.fromJson<app.newsly.core.domain.model.NewsDetailContentLink>() as app.newsly.core.domain.model.BaseDomainModel
                )
            }
            ContentType.BOLD -> {
                app.newsly.core.domain.model.NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = data?.toString()
                        ?.fromJson<app.newsly.core.domain.model.NewsDetailContentTextBold>() as app.newsly.core.domain.model.BaseDomainModel
                )
            }
            null -> {
                app.newsly.core.domain.model.NewsDetailContent(
                    hasValidData = true,
                    type = type,
                    data = null
                )
            }
        }
    }*/
}