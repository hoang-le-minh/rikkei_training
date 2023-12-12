package com.rikkei.training.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rikkei.training.coroutine.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineBinding
    private var isShowData = false
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncrease.setOnClickListener {
            if(!isShowData){
                doIncreaseData()
            }
        }

        binding.btnResult.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            doShowResult()
        }

    }

    private fun doShowResult() {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            delay(6000)
            binding.progressBar.visibility = View.GONE
            binding.txtData.text = "Result: $count"
            isShowData = true
        }
    }

    private fun doIncreaseData() {
        ++count
        binding.txtData.text = "count: $count"
    }
}