package com.rikkei.training.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.coroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCoroutine.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoroutineScope.setOnClickListener {
            val intent = Intent(this, CoroutineScopeActivity::class.java)
            startActivity(intent)
        }
    }
}