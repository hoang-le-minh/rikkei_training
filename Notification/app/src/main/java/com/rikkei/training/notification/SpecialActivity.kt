package com.rikkei.training.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpecialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special)

        supportActionBar?.title = "Special Activity"
    }
}