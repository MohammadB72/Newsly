package app.newsly.core.network.datasource.news

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel

interface NewsRemoteDataSource {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
}