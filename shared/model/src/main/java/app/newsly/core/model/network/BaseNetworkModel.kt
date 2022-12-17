package app.newsly.core.model.network

import app.newsly.core.model.domain.BaseDomainModel

abstract class BaseNetworkModel {
    abstract fun toDomainModel(): BaseDomainModel
}