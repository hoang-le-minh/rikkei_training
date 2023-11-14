package com.rikkei.training.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication: Application() {

    companion object{
        const val CHANNEL_ID = "channel_service"
    }

    override fun onCreate() {
        super.onCreate()

        createChannelNotification()
    }

    private fun createChannelNotification() {
        val channel = NotificationChannel(CHANNEL_ID, "Channel Service", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setSound(null, null)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

}