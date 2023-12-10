package com.rikkei.training.thread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.rikkei.training.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val MESSAGE_COUNT_DOWN = 1000
    private val MESSAGE_COUNT_DOWN_DONE = 1001

    private lateinit var binding: ActivityMainBinding
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            doCountDown()
        }

        binding.btnAsyncTask.setOnClickListener {
            val intent = Intent(this, AsyncTaskActivity::class.java)
            startActivity(intent)
        }

        binding.btnSlideImage.setOnClickListener {
            val intent = Intent(this, SlideImageActivity::class.java)
            startActivity(intent)
        }

        initHandler()

    }

    private fun initHandler(){
        mHandler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                updateUiCountDown(msg)
            }
        }
    }

    private fun updateUiCountDown(msg: Message){
        when(msg.what){
            MESSAGE_COUNT_DOWN -> {
                binding.txtTimer.text = msg.arg1.toString()
                binding.btnCount.isClickable = false
                binding.btnCount.backgroundTintList = resources.getColorStateList(android.R.color.darker_gray)
            }
            MESSAGE_COUNT_DOWN_DONE -> {
                binding.txtTimer.text = msg.arg1.toString()
                binding.btnCount.isClickable = true
                binding.btnCount.backgroundTintList = resources.getColorStateList(R.color.teal_700)
            }
        }
    }

    private fun doCountDown() {
        val thread = Thread {
            var time = 10
            do {
                --time

                val msg = Message()
                msg.what = MESSAGE_COUNT_DOWN
                msg.arg1 = time
                mHandler?.sendMessage(msg)

                Thread.sleep(1000)
            } while (time > 0)
            val msg2 = Message()
            msg2.what = MESSAGE_COUNT_DOWN_DONE
            msg2.arg1 = 10
            mHandler?.sendMessage(msg2)
        }
        thread.start()
    }
}