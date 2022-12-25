package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class NewsDetailContentTextBoldNetworkModel(
    @SerializedName("text") val text: String?
)
