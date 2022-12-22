package app.newsly.core.network.datasource.news

import app.newsly.core.model.NetworkResponse
import app.newsly.core.model.network.NewsNetworkModel
import retrofit2.http.GET

interface NewsApi {
    @GET(value = "news")
    suspend fun getNews(): NetworkResponse<List<NewsNetworkModel>>
}