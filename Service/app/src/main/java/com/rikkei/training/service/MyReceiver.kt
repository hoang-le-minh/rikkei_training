package com.rikkei.training.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val actionMusic = p1?.getIntExtra("action_music", 0)

        val intent = Intent(p0, MyService::class.java)
        intent.putExtra("action_music_service", actionMusic)
        p0?.startService(intent)
    }
}