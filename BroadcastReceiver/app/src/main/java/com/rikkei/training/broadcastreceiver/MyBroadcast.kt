package com.rikkei.training.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.rikkei.training.broadcastreceiver.MainActivity.Companion.MY_ACTION
import com.rikkei.training.broadcastreceiver.MainActivity.Companion.MY_TEXT

class MyBroadcast: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        @Suppress("DEPRECATION")
        if(ConnectivityManager.CONNECTIVITY_ACTION == p1?.action){
            if(p0?.let { networkAvailable(it) } == true){
                Toast.makeText(p0, "Internet Connected", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(p0, "Internet Disconnected", Toast.LENGTH_LONG).show()

            }

        }

        if(MY_ACTION == p1?.action){
            val userString = p1.getStringExtra(MY_TEXT)
            val intent = Intent(p0, MainActivity::class.java)
            intent.putExtra(MY_TEXT, userString)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            p0?.startActivity(intent)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            val network = connectivityManager.activeNetwork ?: return false

            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}