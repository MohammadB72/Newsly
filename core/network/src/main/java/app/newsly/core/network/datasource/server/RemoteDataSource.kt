package app.newsly.core.network.datasource.server

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.EmptyResponseApiModel

interface RemoteDataSource {
    suspend fun getServerStatus(): RequestResult<EmptyResponseApiModel>
}