package app.newsly.core.network.datasource.news

import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel

interface NewsRemoteDataSource {
    suspend fun getNews(): RequestResult<List<NewsApiModel>>
    suspend fun getNewsDetail(): RequestResult<NewsDetailApiModel>
}