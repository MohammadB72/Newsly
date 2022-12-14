package app.newsly.core.network

import app.newsly.core.model.Result
import app.newsly.core.model.network.ServerStatusNetworkModel

interface RemoteDataSource {
    suspend fun getServerStatus(): Result<ServerStatusNetworkModel>
}