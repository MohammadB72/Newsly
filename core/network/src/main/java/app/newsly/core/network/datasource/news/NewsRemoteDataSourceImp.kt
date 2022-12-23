package app.newsly.core.network.datasource.news

import android.content.Context
import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.NewsNetworkModel
import app.newsly.core.network.retrofit.apiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewsRemoteDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val newsApi: NewsApi
) : NewsRemoteDataSource {

    override suspend fun getNews(): RequestResult<List<NewsNetworkModel>> =
        apiCall(
            context = context,
            block = { newsApi.getNews(page = 1).data }
        )
}