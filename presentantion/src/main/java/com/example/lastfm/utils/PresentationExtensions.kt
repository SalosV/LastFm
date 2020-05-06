package com.example.lastfm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import androidx.core.content.ContextCompat.getSystemService
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lastfm.FMApplication


val Context.app: FMApplication
    get() = applicationContext as FMApplication

fun SwipeRefreshLayout.progressHiddenDelay() {
    Handler().postDelayed({
        this.isRefreshing = false
    }, 200)
}

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
    }
    return result
}