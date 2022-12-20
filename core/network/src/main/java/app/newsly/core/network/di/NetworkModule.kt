package app.newsly.core.network.di

import android.content.Context
import app.newsly.core.network.BuildConfig
import app.newsly.core.network.di.qualifier.AuthenticatorRetrofit
import app.newsly.core.network.di.qualifier.GeneralRetrofit
import app.newsly.core.network.retrofit.ServerStatusApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(@GeneralRetrofit retrofit: Retrofit): ServerStatusApi =
        retrofit.create(ServerStatusApi::class.java)

    @Singleton
    @Provides
    @GeneralRetrofit
    fun provideGeneralRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl("https://devopssolutions.ir/digiato/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @AuthenticatorRetrofit
    fun provideAuthenticatorRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl("https://digiato.com/wp-json/digiato/")
        .client(okHttpClient)
        .build()


    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}