package com.rikkei.training.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.notification.databinding.ActivityListUserBinding

class ListUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToUpdate.setOnClickListener {
            val iUpdateActivity = Intent(this, UpdateActivity::class.java)
            startActivity(iUpdateActivity)
        }

        supportActionBar?.title = "ListUserActivity"

    }
}