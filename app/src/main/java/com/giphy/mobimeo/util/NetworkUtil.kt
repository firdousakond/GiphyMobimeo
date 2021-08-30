package com.giphy.mobimeo.util

import android.content.Context
import android.net.ConnectivityManager
import com.giphy.mobimeo.R
import timber.log.Timber


object NetworkUtil {

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else {
            Timber.e(context.getString(R.string.msg_network_unavailable))
            context.toastMessage(context.getString(R.string.msg_network_unavailable))
            false
        }
    }
}