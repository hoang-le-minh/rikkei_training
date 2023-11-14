package com.rikkei.training.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.rikkei.training.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val CHANNEL_ID = "demo_notification"
    private var NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            sendNotification()

        }
    }

    private val bigText = "Message Notification Message Notification Message Notification Message Notification Message Notification"
    private fun sendNotification() {
        createChannelNotification()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title Notification $NOTIFICATION_ID")
            .setContentText("$bigText $NOTIFICATION_ID")
            .setStyle(NotificationCompat.BigTextStyle(). bigText("$bigText $NOTIFICATION_ID"))
            .setSmallIcon(R.drawable.ic_small_notification)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.notify(incrementNotificationId(), notification)
    }

    private fun createChannelNotification() {
        val channel = NotificationChannel(CHANNEL_ID, "Channel Notification", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setSound(null, null)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun incrementNotificationId(): Int{
        return ++NOTIFICATION_ID
    }
}