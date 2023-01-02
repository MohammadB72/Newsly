package app.newsly.core.data.repository.news

import app.newsly.core.model.RequestResult
import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import app.newsly.core.network.model.CategoryApiModel
import app.newsly.core.network.model.NewsApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(page: Int): RequestResult<List<NewsApiModel>> =
        newsRemoteDataSource.getNews(page = page)

    override suspend fun getNewsByCategory(
        categoryId: Int,
        page: Int
    ): RequestResult<List<NewsApiModel>> =
        newsRemoteDataSource.getNewsByCategory(categoryId = categoryId, page = page)

    override suspend fun getNewsDetail(postId: Int): RequestResult<NewsDetailApiModel> =
        newsRemoteDataSource.getNewsDetail(postId = postId)

    override suspend fun getCategories(): RequestResult<List<CategoryApiModel>> =
        newsRemoteDataSource.getCategories()

    override suspend fun getSubCategories(categoryId: Int): RequestResult<List<CategoryApiModel>> =
        newsRemoteDataSource.getSubCategories(categoryId = categoryId)
}