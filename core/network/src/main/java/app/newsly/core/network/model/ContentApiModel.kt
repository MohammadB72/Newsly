package app.newsly.core.network.model


import app.newsly.core.network.model.enums.ContentType
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ContentApiModel(
    @SerializedName("type") val type: ContentType?,
    @SerializedName("attr") private val _attr: JsonObject?
) {
    val attr: String get() = _attr.toString()
}