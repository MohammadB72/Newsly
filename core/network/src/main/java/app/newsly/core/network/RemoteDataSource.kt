package app.newsly.core.network

import app.newsly.core.model.ApiResult
import app.newsly.core.model.network.ServerStatusNetworkModel

interface RemoteDataSource {
    suspend fun getServerStatus(): ApiResult<ServerStatusNetworkModel>
}