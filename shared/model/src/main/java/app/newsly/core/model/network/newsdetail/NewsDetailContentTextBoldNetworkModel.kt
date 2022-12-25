package app.newsly.core.model.network.newsdetail

import com.google.gson.annotations.SerializedName

data class NewsDetailContentTextBoldNetworkModel(
    @SerializedName("text") val text: String?
)
