package com.rikkei.training.activity

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rikkei.training.activity.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Lifecycles", "onCreate: Activity A")
        val pauseB = intent.getIntExtra("pauseB", 0)
        val pauseA = intent.getIntExtra("pauseA", 0)
        intent.removeExtra("pauseA")
        intent.removeExtra("pauseB")
        if(mediaPlayer == null){
            KhoiTaoMediaPlayer()
        }
        mediaPlayer?.seekTo(pauseA)
        mediaPlayer?.start()

        binding.btnAtoB.setOnClickListener {
//            Log.d("position", mediaPlayer?.currentPosition.toString())
            val iB = Intent(this, ActivityB::class.java)
            iB.putExtra("pauseA", mediaPlayer?.currentPosition)
            iB.putExtra("pauseB", pauseB)
            mediaPlayer?.pause()
            startActivity(iB)
            Log.d("Lifecycles", "Activity A -> Activity B")
        }

    }

    override fun onStart() {
        Log.d("Lifecycles", "onStart: Activity A")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Lifecycles", "onResume: Activity A")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Lifecycles", "onPause: Activity A")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Lifecycles", "onStop: Activity A")

        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Lifecycles", "onDestroy: Activity A")
        mediaPlayer?.stop()
        mediaPlayer?.release()
        super.onDestroy()
    }

    private fun KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(this, R.raw.di_du_dua_di)
    }
}