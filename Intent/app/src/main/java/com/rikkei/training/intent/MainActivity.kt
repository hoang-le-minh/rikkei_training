package com.rikkei.training.intent

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.ContentInfoCompat.Flags
import coil.compose.AsyncImage
import com.rikkei.training.intent.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val NAV_CODE = 1

    private val viewModel by viewModels<MediaViewModel>()

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

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.setVideoPath("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4")
        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.btnNavB.setOnClickListener {
            val iB = Intent(this, ActivityB::class.java)
            startActivityForResult(iB, NAV_CODE)
        }

    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect image being shared
            binding.imageView.setImageURI(null)
            binding.imageView.setImageURI(it)
        }
    }

    private fun handleSendVideo(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect video being shared
            binding.videoView.setVideoURI(it)
            binding.videoView.requestFocus()
            binding.videoView.start()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when {
            intent?.action == Intent.ACTION_SEND -> {
                if (intent.type?.startsWith("video/") == true) {
                    handleSendVideo(intent) // Handle text being sent
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }

            else -> {
                binding.videoView.setVideoPath("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4")
                binding.videoView.requestFocus()
                binding.videoView.start()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NAV_CODE && resultCode == RESULT_OK){
            if (data != null) {
                binding.txtResult.text = data.extras?.getString("result") ?: "Name"
            }
        }
    }
}