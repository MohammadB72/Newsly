package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import app.newsly.core.network.model.NewsDetailNetworkModel
import app.newsly.core.network.model.NewsNetworkModel
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(): RequestResult<List<NewsNetworkModel>> =
        newsRemoteDataSource.getNews()

    override suspend fun getNewsDetail(): RequestResult<NewsDetailNetworkModel> =
        newsRemoteDataSource.getNewsDetail()
}