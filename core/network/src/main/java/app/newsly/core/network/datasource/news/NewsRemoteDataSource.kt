package app.newsly.core.network.datasource.news

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel
import app.newsly.core.model.network.newsdetail.NewsDetailNetworkModel

interface NewsRemoteDataSource {
    suspend fun getNews(): RequestResult<List<NewsNetworkModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailNetworkModel>
}