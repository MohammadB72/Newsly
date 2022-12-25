package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel
import app.newsly.core.model.network.newsdetail.NewsDetailNetworkModel

interface NewsRepository {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailNetworkModel>
}