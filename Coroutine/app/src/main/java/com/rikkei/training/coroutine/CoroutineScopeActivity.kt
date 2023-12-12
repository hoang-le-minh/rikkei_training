package com.rikkei.training.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.coroutine.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CoroutineScopeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_scope)

        if(null == savedInstanceState){
            supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
        }

        val scope = CoroutineScope(Job() + Dispatchers.Main)
    }
}