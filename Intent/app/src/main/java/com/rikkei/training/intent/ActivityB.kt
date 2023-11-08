package com.rikkei.training.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.intent.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {

    private lateinit var binding: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNavC.setOnClickListener {
            val iC = Intent(this, ActivityC::class.java)
            iC.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(iC)
            finish()
        }
    }
}