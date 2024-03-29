package app.newsly.core.network.datasource.news

import app.newsly.core.network.model.CategoryApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import app.newsly.core.network.retrofit.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET(value = "news")
    suspend fun getNews(@Query("page") page: Int): NetworkResponse<List<NewsApiModel>>

    @GET(value = "news")
    suspend fun getNewsByCategory(
        @Query("category_id") categoryId: Int,
        @Query("page") page: Int
    ): NetworkResponse<List<NewsApiModel>>

    @GET(value = "news-detail/{post_id}")
    suspend fun getNewsDetail(@Path("post_id") postId: Int): NetworkResponse<NewsDetailApiModel>

    @GET(value = "categories")
    suspend fun getCategories(): NetworkResponse<List<CategoryApiModel>>

    @GET(value = "categories/{category_id}")
    suspend fun getSubCategories(@Path("category_id") categoryId: Int): NetworkResponse<List<CategoryApiModel>>
}