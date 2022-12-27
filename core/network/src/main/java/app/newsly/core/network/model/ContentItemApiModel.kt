package app.newsly.core.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ContentItemApiModel(
    @SerializedName("type") private val _apiType: String?,
    @SerializedName("attr") private val _apiAttr: JsonObject?
) {
    val hasValidContent: Boolean get() = (_apiType != null && _apiAttr != null)
    val apiText: String? get() = _apiAttr?.get("text")?.asString
    val apiHref: String? get() = _apiAttr?.get("href")?.asString

    val apiType: String? get() = _apiType?.lowercase()
    val apiAttr: String get() = _apiAttr.toString()
}