package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.NewsDetailNetworkModel
import app.newsly.core.network.model.NewsNetworkModel

interface NewsRepository {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailNetworkModel>
}