package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(): RequestResult<List<NewsApiModel>> =
        newsRemoteDataSource.getNews()

    override suspend fun getNewsDetail(): RequestResult<NewsDetailApiModel> =
        newsRemoteDataSource.getNewsDetail()
}