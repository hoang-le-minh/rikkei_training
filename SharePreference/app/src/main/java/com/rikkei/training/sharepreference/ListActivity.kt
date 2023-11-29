package com.rikkei.training.sharepreference

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.rikkei.training.sharepreference.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val listAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myViewModel = MyViewModel("Minh Hoang")
        binding.myViewModel = myViewModel

        val username = getEncryptedSharedPreferences().getString("username", "")
        binding.txtEmail.text = username

        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(this)
        listAdapter.submitList(setData())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listAdapter
    }

    private fun setData(): MutableList<User>{
        return mutableListOf(
            User("Hoang1", 21),
            User("Hoang2", 22),
            User("Hoang3", 23),
            User("Hoang4", 24),
            User("Hoang5", 25),
            User("Hoang6", 26),
            User("Hoang7", 27)
        )
    }

    /* ====================== menu logout ================================== */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_logout){
            logout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {

        getEncryptedSharedPreferences().edit()
            .putBoolean("remember", false)
            .apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /* ======================= end menu logout ================================= */

    /* ================= encryptedSharedPreferences =========================*/
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