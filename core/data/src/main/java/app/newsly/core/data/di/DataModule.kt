package app.newsly.core.data.di

import app.newsly.core.data.repository.news.NewsRepository
import app.newsly.core.data.repository.news.NewsRepositoryImp
import app.newsly.core.data.repository.serverstatus.ServerStatusRepository
import app.newsly.core.data.repository.serverstatus.ServerStatusRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsServerStatusRepository(
        serverStatusRepository: ServerStatusRepositoryImp
    ): ServerStatusRepository

    @Binds
    fun bindsNewsRepository(
        newsRepository: NewsRepositoryImp
    ): NewsRepository
}