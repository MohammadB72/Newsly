package app.newsly.core.network.model


import com.google.gson.annotations.SerializedName

data class AuthorApiModel(
    @SerializedName("name") val apiName: String?,
    @SerializedName("avatar") val apiAvatar: String?
)
