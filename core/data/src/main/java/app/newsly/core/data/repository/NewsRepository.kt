package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel

interface NewsRepository {
    suspend fun getNews(): RequestResult<List<NewsApiModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailApiModel>
}