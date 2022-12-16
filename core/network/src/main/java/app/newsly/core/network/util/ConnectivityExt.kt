@file:Suppress("DEPRECATION")

package app.newsly.core.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


val Context.isNetworkConnected: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isCurrentlyConnected()


private fun ConnectivityManager?.isCurrentlyConnected() = when (this) {
    null -> false
    else -> when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ->
            activeNetwork
                ?.let(::getNetworkCapabilities)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        else -> activeNetworkInfo?.isConnected ?: false
    }
}