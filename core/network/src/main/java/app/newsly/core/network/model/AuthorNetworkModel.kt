package app.newsly.core.network.model


import com.google.gson.annotations.SerializedName

data class AuthorNetworkModel(
    @SerializedName("name") val name: String?,
    @SerializedName("avatar") val avatar: String?
)
