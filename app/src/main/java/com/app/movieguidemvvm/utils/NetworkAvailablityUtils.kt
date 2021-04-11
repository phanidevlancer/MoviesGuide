package com.app.movieguidemvvm.utils

import android.content.Context
import android.net.ConnectivityManager


object NetworkAvailablityUtils {

    public fun hasNetworkAvailable(context: Context?): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context?.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return (network?.isConnected) ?: false
    }

}