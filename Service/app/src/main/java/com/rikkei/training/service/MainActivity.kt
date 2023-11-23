package com.rikkei.training.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rikkei.training.service.boundservice.BoundActivity
import com.rikkei.training.service.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSong:Song
    private var isPlaying = false

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val bundle = p1?.extras ?: return
            mSong = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                bundle.getSerializable("object_song", Song::class.java)!!
            } else{
                @Suppress("DEPRECATION")
                bundle.getSerializable("object_song") as Song
            }
            isPlaying = bundle.getBoolean("status_player")
            val actionMusic = bundle.getInt("action_music")

            handleLayoutMusic(actionMusic)
        }

    }

    private fun handleLayoutMusic(actionMusic: Int) {
        when(actionMusic){
            MyService.ACTION_START ->{
                binding.bottomLayout.visibility = View.VISIBLE
                showInfoSong()
                setStatusButtonPlayOrPause()

            }
            MyService.ACTION_PAUSE -> {
                setStatusButtonPlayOrPause()

            }
            MyService.ACTION_RESUME -> {
                setStatusButtonPlayOrPause()

            }
            MyService.ACTION_CLEAR -> {
                binding.bottomLayout.visibility = View.GONE

            }
        }
    }

    private fun showInfoSong() {
        binding.imgIcon.setImageResource(mSong.image)
        binding.txtTitleSong.text = mSong.title
        binding.txtSingleSong.text = mSong.single

        binding.imgPlayOrPause.setOnClickListener {
            if(isPlaying){
                sendActionToService(MyService.ACTION_PAUSE)
            } else {
                sendActionToService(MyService.ACTION_RESUME)
            }
        }

        binding.imgClear.setOnClickListener {
            sendActionToService(MyService.ACTION_CLEAR)
        }
    }

    private fun setStatusButtonPlayOrPause() {
        if(isPlaying){
            binding.imgPlayOrPause.setImageResource(R.drawable.pause_circle)
        } else {
            binding.imgPlayOrPause.setImageResource(R.drawable.play_circle)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter("send_data_to_activity"))

        binding.btnStart.setOnClickListener {
            clickStartService()
        }

        binding.btnStop.setOnClickListener {
            clickStopService()
        }

        binding.btnNavToBoundService.setOnClickListener {
            val intent = Intent(this, BoundActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickStopService() {
        val iService = Intent(this, MyService::class.java)
        stopService(iService)
        binding.bottomLayout.visibility = View.GONE
    }

    private fun clickStartService() {
        val song = Song("Mot cu lua", "Bich Phuong", R.drawable.motculua, R.raw.mot_cu_lua)
        val iService = Intent(this, MyService::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_song", song)
        iService.putExtras(bundle)

        startService(iService)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    private fun sendActionToService(action: Int){
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("action_music_service", action)
        startService(intent)
    }
}