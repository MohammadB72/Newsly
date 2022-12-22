package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel

interface NewsRepository {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
}