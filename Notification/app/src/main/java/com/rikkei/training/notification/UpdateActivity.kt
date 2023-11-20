package com.rikkei.training.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.notification.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToSpecial.setOnClickListener {
            val iSpecialActivity = Intent(this, SpecialActivity::class.java)
            startActivity(iSpecialActivity)
        }
        supportActionBar?.title = "UpdateActivity"

    }
}