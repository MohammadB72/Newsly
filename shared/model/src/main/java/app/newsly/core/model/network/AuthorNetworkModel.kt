package app.newsly.core.model.network

import app.newsly.core.model.domain.Author
import app.newsly.core.model.extension.isNotNullAndBlank
import com.google.gson.annotations.SerializedName

data class AuthorNetworkModel(
    @SerializedName("name") val name: String?,
    @SerializedName("avatar") val avatar: String?
) : BaseNetworkModel() {
    override fun toDomainModel(): Author =
        Author(
            hasValidData = name.isNotNullAndBlank() || avatar.isNotNullAndBlank(),
            name = name ?: "",
            avatar = avatar ?: ""
        )
}
