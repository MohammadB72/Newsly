package app.newsly.core.network.datasource.server

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.EmptyResponseNetworkModel

interface RemoteDataSource {
    suspend fun getServerStatus(): RequestResult<EmptyResponseNetworkModel>
}