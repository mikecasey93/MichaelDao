package com.example.android.codelabs.paging.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class NetworkChangedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (!isDeviceOnline(context)) {
            Toast.makeText(
                context,
                "You are currently offline",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                context,
                "You are online",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isDeviceOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}