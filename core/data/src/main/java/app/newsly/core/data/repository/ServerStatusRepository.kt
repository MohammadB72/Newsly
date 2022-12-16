package app.newsly.core.data.repository

import app.newsly.core.model.ApiResult
import app.newsly.core.model.network.ServerStatusNetworkModel

interface ServerStatusRepository {
    suspend fun getServerStatus(): ApiResult<ServerStatusNetworkModel>
}