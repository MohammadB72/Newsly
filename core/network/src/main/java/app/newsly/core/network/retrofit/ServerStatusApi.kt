package app.newsly.core.network.retrofit

import app.newsly.core.model.network.ServerStatusNetworkModel
import retrofit2.http.GET

interface ServerStatusApi {
    @GET(value = "digiato-app.php")
    suspend fun getServerStatus(): ServerStatusNetworkModel
}