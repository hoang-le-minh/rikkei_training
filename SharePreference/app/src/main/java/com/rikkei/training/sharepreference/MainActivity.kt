package com.rikkei.training.sharepreference

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.rikkei.training.sharepreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val remember = getEncryptedSharedPreferences().getBoolean("remember", false)
        if(remember){
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

        supportActionBar?.hide()

    }

    private fun login() {
        val username = binding.edtName.text.toString()
        val password = binding.edtPassword.text.toString()

        val cbRemember = binding.cbRemember

        getEncryptedSharedPreferences().edit().putString("username", username).apply()
        if(cbRemember.isChecked){
            getEncryptedSharedPreferences().edit()
                .putBoolean("remember", true)
                .apply()
        }

        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getEncryptedSharedPreferences(): SharedPreferences {
        val masterKey = MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            this,
            getString(R.string.pref_login_key),
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}