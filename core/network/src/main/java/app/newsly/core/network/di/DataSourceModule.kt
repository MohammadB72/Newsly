package app.newsly.core.network.di


import app.newsly.core.network.RemoteDataSource
import app.newsly.core.network.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bind(source: RemoteDataSourceImp): RemoteDataSource
}