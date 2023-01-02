package app.newsly.core.data.repository.news

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.CategoryApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel

interface NewsRepository {
    suspend fun getNews(page: Int): RequestResult<List<NewsApiModel>>
    suspend fun getNewsByCategory(categoryId: Int, page: Int): RequestResult<List<NewsApiModel>>
    suspend fun getNewsDetail(postId: Int): RequestResult<NewsDetailApiModel>
    suspend fun getCategories(): RequestResult<List<CategoryApiModel>>
    suspend fun getSubCategories(categoryId: Int): RequestResult<List<CategoryApiModel>>
}