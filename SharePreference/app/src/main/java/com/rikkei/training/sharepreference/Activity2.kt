package com.rikkei.training.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ObservableField
import com.rikkei.training.sharepreference.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val defaultContent = ObservableField<String>()
        defaultContent.set("Default Content")
        val activity2ViewModel = Activity2ViewModel(defaultContent)
        binding.activity2viewmodel = activity2ViewModel
    }
}