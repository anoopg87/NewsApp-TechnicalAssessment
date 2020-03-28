package com.anooplab.newsapp.util

import android.content.Context
import android.net.ConnectivityManager

interface IConnectionManager {
    fun isConnectedToInternet(): Boolean
}

class ConnectionManager(
    private val context: Context
) : IConnectionManager {
    override fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        val activeNetwork = connectivityManager.activeNetworkInfo
        isConnected = activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting
        return isConnected
    }
}