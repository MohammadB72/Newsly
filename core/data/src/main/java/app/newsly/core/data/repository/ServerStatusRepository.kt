package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.EmptyResponseApiModel

interface ServerStatusRepository {
    suspend fun getServerStatus(): RequestResult<EmptyResponseApiModel>
}