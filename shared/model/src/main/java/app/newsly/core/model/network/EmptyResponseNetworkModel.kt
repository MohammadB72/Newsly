package app.newsly.core.model.network

import app.newsly.core.model.domain.EmptyResponse


class EmptyResponseNetworkModel : BaseNetworkModel() {
    override fun toDomainModel() = EmptyResponse()
}
