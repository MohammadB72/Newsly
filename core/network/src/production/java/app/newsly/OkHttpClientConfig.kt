package app.newsly

import android.content.Context
import app.newsly.core.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


fun OkHttpClient.Builder.setEnvironment(applicationContext: Context): OkHttpClient.Builder =
    this.apply {
        connectTimeout(10, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

