package com.rikkei.training.service.boundservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import com.rikkei.training.service.R

const val MSG_MUSIC = 1

class MusicBoundService2: Service() {

    private var mediaPlayer: MediaPlayer? = null

    private lateinit var mMessenger: Messenger

    inner class IncomingHandler(
        context: Context
    ): Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                MSG_MUSIC -> {
                    startMusic()
                }
                else -> super.handleMessage(msg)
            }

        }

    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d("MusicBoundService", "onBind")
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MusicBoundService", "onCreate")

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

    private fun startMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.mot_cu_lua)
        }
        mediaPlayer?.start()
    }
}