package app.newsly.core.network

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.ServerStatusNetworkModel

interface RemoteDataSource {
    suspend fun getServerStatus(): RequestResult<ServerStatusNetworkModel>
}