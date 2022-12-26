package app.newsly.core.network.model


import app.newsly.core.network.model.enums.ContentType
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ContentApiModel(
    @SerializedName("type") val type: ContentType?,
    @SerializedName("attr") val attr: JsonObject?
) {
    val text: String? get() = attr?.get("text")?.asString
    val href: String? get() = attr?.get("href")?.asString
}