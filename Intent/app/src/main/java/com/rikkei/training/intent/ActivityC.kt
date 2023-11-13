package com.rikkei.training.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rikkei.training.intent.databinding.ActivityCBinding

class ActivityC : AppCompatActivity() {

    private lateinit var binding: ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinishC.setOnClickListener {

            val iResult = Intent()
            val name = binding.txtName.text.toString()
            iResult.putExtra("result", name)
            setResult(RESULT_OK, iResult)
            finish()
        }

        binding.btnNav1.setOnClickListener {
            val i1 = Intent(this, Activity1::class.java)
            startActivity(i1)
        }

    }
}