package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel
import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(): RequestResult<List<NewsNetworkModel>> =
        newsRemoteDataSource.getNews()
}