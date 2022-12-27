package app.newsly.core.network.datasource.serverstatus

import app.newsly.core.network.model.EmptyResponseApiModel
import retrofit2.http.GET

interface ServerStatusApi {
    @GET(value = "server-status")
    suspend fun getServerStatus(): EmptyResponseApiModel
}