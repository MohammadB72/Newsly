package app.newsly

import android.content.Context
import app.newsly.mock.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


fun OkHttpClient.Builder.setEnvironment(applicationContext: Context): OkHttpClient.Builder =
    this.apply {
        connectTimeout(1, TimeUnit.SECONDS)
        writeTimeout(1, TimeUnit.SECONDS)
        readTimeout(1, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        addInterceptor(MockInterceptor(applicationContext))
    }

