package app.newsly.core.network.di

import android.content.Context
import app.newsly.ApiConfig
import app.newsly.core.network.model.EmptyResponseConvertor
import app.newsly.core.network.retrofit.ServerStatusApi
import app.newsly.setEnvironment
import com.google.gson.Gson
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
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}