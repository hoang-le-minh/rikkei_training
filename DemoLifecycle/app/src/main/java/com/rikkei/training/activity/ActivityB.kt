package com.rikkei.training.activity

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.rikkei.training.activity.databinding.ActivityBBinding

private lateinit var binding: ActivityBBinding
class ActivityB : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Lifecycles", "onCreate: Activity B")

        val pauseB = intent.getIntExtra("pauseB", 0)
        val pauseA = intent.getIntExtra("pauseA", 0)
        intent.removeExtra("pauseA")
        intent.removeExtra("pauseB")

        if(mediaPlayer == null){
            KhoiTaoMediaPlayer()
        }
        mediaPlayer?.seekTo(pauseB)
        mediaPlayer?.start()

        binding.btnBtoA.setOnClickListener {
            val iA = Intent(this, MainActivity::class.java)
            iA.putExtra("pauseB", mediaPlayer?.currentPosition)
            iA.putExtra("pauseA", pauseA)
            mediaPlayer?.pause()
            startActivity(iA)
            Log.d("Lifecycles", "Activity B -> Activity A")
        }

    }

    override fun onStart() {
        Log.d("Lifecycles", "onStart: Activity B")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Lifecycles", "onResume: Activity B")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Lifecycles", "onPause: Activity B")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Lifecycles", "onStop: Activity B")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Lifecycles", "onDestroy: Activity B")
        mediaPlayer?.stop()
        mediaPlayer?.release()
        super.onDestroy()
    }

    private fun KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(this, R.raw.mot_cu_lua)
    }

}