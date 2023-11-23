package com.rikkei.training.service.boundservice

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rikkei.training.service.R
import com.rikkei.training.service.Song
import com.rikkei.training.service.databinding.ActivityBoundBinding

class BoundActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoundBinding
    private lateinit var mBoundService: MusicBoundService
    private var mBound: Boolean = false
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as MusicBoundService.MyBinder
            mBoundService = binder.getBoundService()
            mBound = true

            handleBottomLayout()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }

    }

    private var mService: Messenger? = null
    private var bound: Boolean = false
    private val mConnection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mService = Messenger(p1)
            bound = true

            sendMessagePlayMusic()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
            bound = false
        }

    }

    private fun sendMessagePlayMusic() {
        val message = Message.obtain(null, MSG_MUSIC, 0, 0)
        try {
            mService?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartBoundService.setOnClickListener {
            onClickStartService()
        }

        binding.btnStopBoundService.setOnClickListener {
            onClickStopService()
        }

        binding.btnStartBoundService2.setOnClickListener {
            onClickStartBoundService2()
        }

        binding.btnStopBoundService2.setOnClickListener {
            onClickStopBoundService2()
        }

        binding.imgPlayOrPause.setOnClickListener {
            if(mBoundService.isPlaying()){
                mBoundService.pauseMusic()
            } else {
                mBoundService.resumeMusic()
            }

            setStatusPlayOrPause()
        }

        binding.imgClear.setOnClickListener {
            onClickStopService()
        }

    }

    private fun onClickStopBoundService2() {
        if(bound){
            unbindService(mConnection)
            bound = false
        }
    }

    private fun onClickStartBoundService2() {
        val musicIntent = Intent(this, MusicBoundService2::class.java)
        bindService(musicIntent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private fun onClickStopService() {
        val intent = Intent(this, MusicBoundService::class.java)
        stopService(intent)
        if(mBound){
            unbindService(mServiceConnection)
            mBound = false
        }
        binding.bottomLayout.visibility = View.GONE
    }

    private fun onClickStartService() {
        val song = Song("Mot cu lua", "Bich Phuong", R.drawable.motculua, R.raw.mot_cu_lua)
        val bundle = Bundle()
        bundle.putSerializable("object_song", song)
        val musicIntent = Intent(this, MusicBoundService::class.java)
        onClickStopService()
        musicIntent.putExtras(bundle)

        startService(musicIntent)
        bindService(musicIntent, mServiceConnection, Service.BIND_AUTO_CREATE)
    }

    private fun handleBottomLayout(){
        binding.bottomLayout.visibility = View.VISIBLE
        binding.txtTitleSong.text = mBoundService.getSong().title
        binding.txtSingleSong.text = mBoundService.getSong().single

        setStatusPlayOrPause()
    }

    private fun setStatusPlayOrPause(){
        if(mBoundService.isPlaying()){
            binding.imgPlayOrPause.setImageResource(R.drawable.pause_circle)
        } else {
            binding.imgPlayOrPause.setImageResource(R.drawable.play_circle)
        }
    }

}