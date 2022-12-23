package app.newsly.core.network.datasource.news

import app.newsly.core.model.NetworkResponse
import app.newsly.core.model.network.NewsNetworkModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(value = "news")
    suspend fun getNews(@Query("page") page: Int): NetworkResponse<List<NewsNetworkModel>>
}