package com.rikkei.training.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.rikkei.training.notification.MyApplication.Companion.CHANNEL_ID1
import com.rikkei.training.notification.MyApplication.Companion.CHANNEL_ID2
import com.rikkei.training.notification.MyApplication.Companion.CHANNEL_ID3
import com.rikkei.training.notification.MyApplication.Companion.NOTIFICATION_ID
import com.rikkei.training.notification.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            sendNotification()

        }

        binding.btnCustom.setOnClickListener {
            sendCustomNotification()
        }

        binding.btnGoToListUser.setOnClickListener {
            val iListUser = Intent(this, ListUserActivity::class.java)
            startActivity(iListUser)
        }

        binding.btnSendMedia.setOnClickListener {
            sendNotificationMedia()
        }

        supportActionBar?.title = "MainActivity"
    }

    private fun sendNotificationMedia(){

        val mediaSession = MediaSessionCompat(this, "tag")
        mediaSession.isActive = true

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.motculua)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID3)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_music_note)
            // Add media control buttons that invoke intents in your media service
            .addAction(R.drawable.ic_skip_prev, "Previous", null) // #0
            .addAction(R.drawable.pause_circle, "Pause", null) // #1
            .addAction(R.drawable.ic_skip_next, "Next", null) // #2
            // Apply the media style template.
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2)
                .setMediaSession(mediaSession.sessionToken))
            .setContentTitle("Title")
            .setSubText("Album Name")
            .setContentText("Single")
            .setLargeIcon(bitmap)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.notify(incrementNotificationId(), notification)
    }

    private fun sendCustomNotification() {

        // start special activity from notification
        val notifyIntent = Intent(this, SpecialActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, NOTIFICATION_ID, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // collapsed
        val remoteViews = RemoteViews(packageName, R.layout.layout_custom_notification)
        remoteViews.setTextViewText(R.id.txt_title_custom_notification, "Title custom notification $NOTIFICATION_ID")
        remoteViews.setTextViewText(R.id.txt_message_custom_notification, "$bigText $NOTIFICATION_ID")
        val sdf = SimpleDateFormat("HH:mm:ss")
        val strTime = sdf.format(Date())
        remoteViews.setTextViewText(R.id.txt_time_custom_notification, strTime)

        // expanded
        val remoteViewsExpanded = RemoteViews(packageName, R.layout.layout_custom_notification_expanded)
        remoteViewsExpanded.setTextViewText(R.id.txt_title_custom_notification_expanded, "Title custom notification $NOTIFICATION_ID")
        remoteViewsExpanded.setTextViewText(R.id.txt_message_custom_notification_expanded, "$bigText $NOTIFICATION_ID")
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.motculua)
        remoteViewsExpanded.setImageViewBitmap(R.id.img_expanded, bitmap)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID2)
            .setStyle(NotificationCompat.BigTextStyle(). bigText("$bigText $NOTIFICATION_ID"))
            .setSmallIcon(R.drawable.ic_small_notification)
            .setCustomContentView(remoteViews)
            .setCustomBigContentView(remoteViewsExpanded)
            .setContentIntent(notifyPendingIntent)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.notify(incrementNotificationId(), notification)
    }

    private val bigText = "Message Notification Message Notification Message Notification Message Notification Message Notification"
    private fun sendNotification() {
//        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.play}")

        // start an activity from a notification
        // Create an Intent for the activity you want to start.
        val resultIntent = Intent(this, UpdateActivity::class.java)
        // Create the TaskStackBuilder.
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack.
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack.
            getPendingIntent(
                NOTIFICATION_ID,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID1)
            .setContentTitle("Title Notification $NOTIFICATION_ID")
            .setContentText("$bigText $NOTIFICATION_ID")
            .setStyle(NotificationCompat.BigTextStyle(). bigText("$bigText $NOTIFICATION_ID"))
            .setSmallIcon(R.drawable.ic_small_notification)
            .setSound(uri)
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.notify(incrementNotificationId(), notification)
    }

    private fun incrementNotificationId(): Int{
        return ++NOTIFICATION_ID
    }


}