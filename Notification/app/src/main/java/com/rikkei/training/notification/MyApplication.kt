package com.rikkei.training.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.net.Uri

class MyApplication: Application() {

    companion object {
        val CHANNEL_ID1 = "demo_notification1"
        val CHANNEL_ID2 = "demo_notification2"
        val CHANNEL_ID3 = "demo_notification3"
        var NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        createChannelNotification()
    }


    private fun createChannelNotification() {

        val uri = Uri.parse("android.resource://${packageName}/${R.raw.play}")

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        val channel = NotificationChannel(CHANNEL_ID1, "Channel Notification", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setSound(uri, audioAttributes)
        val manager = getSystemService(NotificationManager::class.java)


        val channel2 = NotificationChannel(CHANNEL_ID2, "Channel Notification", NotificationManager.IMPORTANCE_HIGH)
        val channel3 = NotificationChannel(CHANNEL_ID3, "Channel Notification", NotificationManager.IMPORTANCE_DEFAULT)
        manager?.let{
            it.createNotificationChannel(channel)
            it.createNotificationChannel(channel2)
            it.createNotificationChannel(channel3)
        }
    }

}