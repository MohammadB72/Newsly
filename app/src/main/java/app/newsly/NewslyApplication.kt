package app.newsly

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewslyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}