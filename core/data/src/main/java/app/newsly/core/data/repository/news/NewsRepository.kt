package app.newsly.core.data.repository.news

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.CategoriesApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel

interface NewsRepository {
    suspend fun getNews(): RequestResult<List<NewsApiModel>>
    suspend fun getNewsDetail(postId: Int): RequestResult<NewsDetailApiModel>
    suspend fun getCategories(): RequestResult<List<CategoriesApiModel>>
}