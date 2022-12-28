package app.newsly.core.network.model

import com.google.gson.annotations.SerializedName

data class CategoriesApiModel(
    @SerializedName("id") val apiId: Int?,
    @SerializedName("title") val apiTitle: String?,
    @SerializedName("posts_count") val apiPostsCount: Int?,
    @SerializedName("icon") val apiIcon: String?,
)
