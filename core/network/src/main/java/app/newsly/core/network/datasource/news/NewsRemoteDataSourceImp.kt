package app.newsly.core.network.datasource.news

import android.content.Context
import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.CategoryApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import app.newsly.core.network.retrofit.apiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewsRemoteDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val newsApi: NewsApi
) : NewsRemoteDataSource {

    override suspend fun getNews(page: Int): RequestResult<List<NewsApiModel>> =
        apiCall(
            context = context,
            block = { newsApi.getNews(page = page).data }
        )

    override suspend fun getNewsByCategory(
        categoryId: Int,
        page: Int
    ): RequestResult<List<NewsApiModel>> =
        apiCall(
            context = context,
            block = { newsApi.getNewsByCategory(categoryId = categoryId, page = 1).data }
        )

    override suspend fun getNewsDetail(postId: Int): RequestResult<NewsDetailApiModel> =
        apiCall(
            context = context,
            block = { newsApi.getNewsDetail(postId = postId).data }
        )

    override suspend fun getCategories(): RequestResult<List<CategoryApiModel>> =
        apiCall(
            context = context,
            block = { newsApi.getCategories().data }
        )

    override suspend fun getSubCategories(categoryId: Int): RequestResult<List<CategoryApiModel>> =
        apiCall(
            context = context,
            block = { newsApi.getSubCategories(categoryId = categoryId).data }
        )
}