package app.newsly.core.network.di


import app.newsly.core.network.datasource.news.NewsRemoteDataSource
import app.newsly.core.network.datasource.news.NewsRemoteDataSourceImp
import app.newsly.core.network.datasource.serverstatus.ServerStatusRemoteDataSource
import app.newsly.core.network.datasource.serverstatus.ServerStatusRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindServerStatusRemoteDataSource(source: ServerStatusRemoteDataSourceImp): ServerStatusRemoteDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource(source: NewsRemoteDataSourceImp): NewsRemoteDataSource
}