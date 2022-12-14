package app.newsly.core.data.repository

import app.newsly.core.model.Result
import app.newsly.core.model.network.ServerStatusNetworkModel

interface ServerStatusRepository {
    suspend fun getServerStatus(): Result<ServerStatusNetworkModel>
}