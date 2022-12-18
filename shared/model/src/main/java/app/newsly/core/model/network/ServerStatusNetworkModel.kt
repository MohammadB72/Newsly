package app.newsly.core.model.network


import app.newsly.core.model.domain.ServerStatus
import kotlinx.serialization.Serializable


@Serializable
data class ServerStatusNetworkModel(
    val up: Boolean,
    val title: String,
    val message: String,
) : BaseNetworkModel() {
    override fun toDomainModel(): ServerStatus = ServerStatus(isUp = up)
}
