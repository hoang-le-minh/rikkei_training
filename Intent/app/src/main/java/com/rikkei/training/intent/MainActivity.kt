package com.rikkei.training.intent

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.Toast
import com.rikkei.training.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnOpenCamera.setOnClickListener {
            val iCamera = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            iCamera.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)
            if(iCamera.resolveActivity(packageManager) != null){
                startActivity(iCamera)
            } else {
                Toast.makeText(this, "Camera application not found!", Toast.LENGTH_LONG).show()
            }
        }

        binding.videoView.setVideoPath("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4")
        binding.videoView.start()


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)

        }
    }
}