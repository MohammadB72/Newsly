package app.newsly.core.network

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.EmptyResponseNetworkModel

interface RemoteDataSource {
    suspend fun getServerStatus(): RequestResult<EmptyResponseNetworkModel>
}