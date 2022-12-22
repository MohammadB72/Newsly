package app.newsly.core.network.datasource.server

import app.newsly.core.model.network.EmptyResponseNetworkModel
import retrofit2.http.GET

interface ServerStatusApi {
    @GET(value = "server-status")
    suspend fun getServerStatus(): EmptyResponseNetworkModel
}