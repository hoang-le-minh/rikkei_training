package com.rikkei.training.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.rikkei.training.broadcastreceiver.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        const val MY_ACTION = "com.rikkei.training.ACTION"
        const val MY_TEXT = "com.rikkei.training.USER"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var broadcastReceiver: MyBroadcast

//    private val broadcastSend = object : BroadcastReceiver(){
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            if (MY_ACTION == p1?.action){
//                val text = p1.getStringExtra(MY_TEXT)
//                binding.txtReceiver.text = text
//            }
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            clickSendBroadcast()
        }

        val intent = intent
        intent?.let {
            val userListString = it.getStringExtra(MY_TEXT)
            val listUser = mutableListOf<User>()
            var user: User
            val gson = Gson()
            var jsonObject: JSONObject

            if(userListString != null){
                val jsonArray = JSONArray(userListString)
                for (i in 0 until jsonArray.length()){
                    jsonObject = jsonArray.getJSONObject(i)
                    user = gson.fromJson(jsonObject.toString(), User::class.java)
                    listUser.add(user)
                }
                var id = ""
                var name = ""
                for (i in 0 until listUser.size){
                    id += "${listUser[i].id} | "
                    name += "${listUser[i].name} | "
                }

                binding.txtId.text = id.dropLast(2)
                binding.txtName.text = name.dropLast(2)
            }

        }

        broadcastReceiver = MyBroadcast()
    }

    private fun clickSendBroadcast() {
        val intent = Intent(MY_ACTION)
        val user1 = User(1, "Hoang")
        val user2 = User(2, "Minh")
        val list = listOf(user1, user2)

        val gson = Gson()
        val jsonArray = gson.toJsonTree(list).asJsonArray
        val strJson = jsonArray.toString()

        intent.putExtra(MY_TEXT, strJson)
        sendBroadcast(intent)
        Toast.makeText(this, "Send broadcast successfully!", Toast.LENGTH_SHORT).show()
    }

    @Suppress("DEPRECATION")
    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(MY_ACTION)

        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}