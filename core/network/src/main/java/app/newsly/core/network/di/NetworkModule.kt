package app.newsly.core.network.di

import android.content.Context
import app.newsly.ApiConfig
import app.newsly.core.network.datasource.news.NewsApi
import app.newsly.core.network.datasource.serverstatus.ServerStatusApi
import app.newsly.core.network.model.EmptyResponseConvertor
import app.newsly.setEnvironment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ServerStatusApi =
        retrofit.create(ServerStatusApi::class.java)

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideGeneralRetrofit(
        okHttpClient: OkHttpClient,
        emptyResponseConvertor: EmptyResponseConvertor,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addConverterFactory(emptyResponseConvertor)
            .baseUrl(ApiConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder().setEnvironment(context).build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}