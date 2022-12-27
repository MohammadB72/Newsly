package app.newsly.core.network.datasource.serverstatus

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.EmptyResponseApiModel

interface ServerStatusRemoteDataSource {
    suspend fun getServerStatus(): RequestResult<EmptyResponseApiModel>
}