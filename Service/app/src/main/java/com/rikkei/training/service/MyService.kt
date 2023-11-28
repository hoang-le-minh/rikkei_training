package com.rikkei.training.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadata
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rikkei.training.service.MyApplication.Companion.CHANNEL_ID

class MyService: Service() {
    companion object {
        const val ACTION_PAUSE = 1
        const val ACTION_RESUME = 2
        const val ACTION_CLEAR = 3
        const val ACTION_START = 4
    }

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var mSong:Song
    private var isPlaying = false

    override fun onCreate() {
        super.onCreate()
        Log.e("serviceLifecycle", "onCreate: MyService")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song: Song?
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            song = intent?.getSerializableExtra("object_song", Song::class.java)
        } else {
            @Suppress("DEPRECATION")
            song = intent?.getSerializableExtra("object_song") as? Song
        }

        if(song != null){
            mSong = song
            startMusic(song)
            sendNotificationMedia(song)
        }


        val actionMusic = intent?.getIntExtra("action_music_service", 0)
        handleActionMusic(actionMusic!!)

        return START_NOT_STICKY
    }

    private fun startMusic(song: Song?) {
        if (song != null && mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(applicationContext, song.resource)
        }
        mediaPlayer?.start()
        isPlaying = true
        sendActionToActivity(ACTION_START)
    }

//    private fun sendDataNotification(song: Song?) {
//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val bitmap = song?.image?.let { BitmapFactory.decodeResource(resources, it) }
//
//        val remoteViews = RemoteViews(packageName, R.layout.custom_notification)
//        remoteViews.setTextViewText(R.id.txt_title_song, song?.title)
//        remoteViews.setTextViewText(R.id.txt_single_song, song?.single)
//        remoteViews.setImageViewBitmap(R.id.img_icon, bitmap)
//        remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.play_circle)
//
//        if(isPlaying){
//            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause, getPendingIntent(this, ACTION_PAUSE))
//            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.pause_circle)
//        } else {
//            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause, getPendingIntent(this, ACTION_RESUME))
//            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.play_circle)
//        }
//        remoteViews.setOnClickPendingIntent(R.id.img_clear, getPendingIntent(this, ACTION_CLEAR))
//
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_service)
//            .setContentIntent(pendingIntent)
//            .setCustomContentView(remoteViews)
//            .setSound(null)
//            .build()
//
//        startForeground(1, notification)
//    }

    private fun sendNotificationMedia(song: Song?){
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.motculua)
        val mediaSession = MediaSessionCompat(this, "SESSION_TAG")
        mediaSession.isActive = true
        mediaSession.setMetadata(
            MediaMetadataCompat.Builder()
                .putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, bitmap)
                .putString(MediaMetadata.METADATA_KEY_TITLE, song?.title)
                .putString(MediaMetadata.METADATA_KEY_ARTIST, song?.single)
                .build()
        )

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_music_note)

            // Apply the media style template.
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2)
//                .setMediaSession(mediaSession.sessionToken)
            )
            .setContentTitle(song?.title)
            .setSubText("Album Name")
            .setContentText(song?.single)
            .setLargeIcon(bitmap)

        if(isPlaying){
            notificationBuilder
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_skip_prev, "Previous", null) // #0
                .addAction(R.drawable.pause_circle, "Pause", getPendingIntent(this, ACTION_PAUSE)) // #1
                .addAction(R.drawable.ic_skip_next, "Next", null) // #2
        } else {
            notificationBuilder
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_skip_prev, "Previous", null) // #0
                .addAction(R.drawable.play_circle, "Play", getPendingIntent(this, ACTION_RESUME)) // #1
                .addAction(R.drawable.ic_skip_next, "Next", null) // #2
        }

        val notification = notificationBuilder.build()

        startForeground(1, notification)
    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent{
        val intent = Intent(this, MyReceiver::class.java)
        intent.putExtra("action_music", action)

        return PendingIntent.getBroadcast(context.applicationContext, action, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun handleActionMusic(action: Int){
        when(action){
            ACTION_PAUSE -> pauseMusic()
            ACTION_RESUME -> resumeMusic()
            ACTION_CLEAR -> {
                stopSelf()
                sendActionToActivity(ACTION_CLEAR)
            }
        }
    }


    private fun resumeMusic() {
        if(mediaPlayer != null && !isPlaying){
            mediaPlayer?.start()
            isPlaying = true
            Log.d("songInfo", mSong.toString())
            sendActionToActivity(ACTION_RESUME)
            sendNotificationMedia(mSong)
        }
    }

    private fun pauseMusic() {
        if(mediaPlayer != null && isPlaying){
            mediaPlayer?.pause()
            isPlaying = false
            Log.d("songInfo", mSong.toString())
            sendActionToActivity(ACTION_PAUSE)
            sendNotificationMedia(mSong)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("serviceLifecycle", "onDestroy: MyService")
        if(mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }

    }

    private fun sendActionToActivity(action: Int){
        val intent = Intent("send_data_to_activity")
        val bundle = Bundle()
        bundle.putSerializable("object_song", mSong)
        bundle.putBoolean("status_player", isPlaying)
        bundle.putInt("action_music", action)

        intent.putExtras(bundle)

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}