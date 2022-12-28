package app.newsly.core.network.datasource.news

import app.newsly.core.network.model.CategoriesApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import app.newsly.core.network.retrofit.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET(value = "news")
    suspend fun getNews(@Query("page") page: Int): NetworkResponse<List<NewsApiModel>>

    @GET(value = "news-detail/{post_id}")
    suspend fun getNewsDetail(@Path("post_id") postId: Int): NetworkResponse<NewsDetailApiModel>

    @GET(value = "categories")
    suspend fun getCategories(): NetworkResponse<List<CategoriesApiModel>>
}