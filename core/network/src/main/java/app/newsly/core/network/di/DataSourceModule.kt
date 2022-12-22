package app.newsly.core.network.di


import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import app.newsly.core.network.datasource.news.NewsRemoteDataSourceImp
import app.newsly.core.network.datasource.server.RemoteDataSource
import app.newsly.core.network.datasource.server.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(source: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource(source: NewsRemoteDataSourceImp): NewsRemoteDataSource
}