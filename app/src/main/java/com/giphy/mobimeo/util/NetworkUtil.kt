package com.giphy.mobimeo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.giphy.mobimeo.R

object NetworkUtil {

    fun isNetworkConnected(context: Context): Boolean {
        val result: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        if (!result) {
            context.toastMessage(context.getString(R.string.msg_network_unavailable))
        }
        return result
    }
}