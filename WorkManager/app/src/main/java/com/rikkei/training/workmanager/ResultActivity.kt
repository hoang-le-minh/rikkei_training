package com.rikkei.training.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.workmanager.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.extras?.getInt(RESULT, 0)
        binding.txtResult.text = result.toString()
    }
}