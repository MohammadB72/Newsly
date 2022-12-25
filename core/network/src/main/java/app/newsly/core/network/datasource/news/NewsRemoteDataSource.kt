package app.newsly.core.network.datasource.news

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.NewsDetailNetworkModel
import app.newsly.core.network.model.NewsNetworkModel

interface NewsRemoteDataSource {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailNetworkModel>
}