package com.rikkei.training.service.boundservice

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.rikkei.training.service.MyApplication
import com.rikkei.training.service.MyService
import com.rikkei.training.service.R
import com.rikkei.training.service.Song

class MusicBoundService: Service() {

    private val binder = MyBinder()
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private lateinit var mSong: Song

    inner class MyBinder: Binder(){
        fun getBoundService(): MusicBoundService = this@MusicBoundService
    }
    override fun onBind(p0: Intent?): IBinder? {
        Log.d("MusicBoundService", "onBind")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MusicBoundService", "onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MusicBoundService", "onStartCommand")
        val song: Song?
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            song = intent?.getSerializableExtra("object_song", Song::class.java)
        } else {
            @Suppress("DEPRECATION")
            song = intent?.getSerializableExtra("object_song") as? Song
        }

        if(song != null){
            mSong = song
            startMusic(mSong)
            sendNotification(mSong)
        }

        return START_NOT_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MusicBoundService", "onUnBind")
        return super.onUnbind(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MusicBoundService", "onDestroy")
        if(mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun startMusic(song: Song){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(applicationContext, song.resource)
        }
        mediaPlayer?.start()
        isPlaying = true
    }


    private fun sendNotification(song: Song) {
        val bitmap = BitmapFactory.decodeResource(resources, song.image)
        val notificationBuilder = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setContentTitle(song.title)
            .setContentText(song.single)
            .setSmallIcon(R.drawable.ic_music_note)
            .setLargeIcon(bitmap)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0))

        if(isPlaying){
            notificationBuilder.addAction(R.drawable.pause_circle, "Pause", null)
        } else {
            notificationBuilder.addAction(R.drawable.play_circle, "Play", null)
        }

        val notification = notificationBuilder.build()

        startForeground(1, notification)
    }

    fun pauseMusic(){
        if(mediaPlayer != null && isPlaying){
            mediaPlayer?.pause()
            isPlaying = false
        }
    }

    fun resumeMusic(){
        if(mediaPlayer != null && !isPlaying){
            mediaPlayer?.start()
            isPlaying = true
        }
    }

    fun getSong(): Song{
        return mSong
    }

    fun isPlaying(): Boolean{
        return isPlaying
    }

}