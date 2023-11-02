package com.rikkei.training.matchimagegame

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikkei.training.matchimagegame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_playFragment)
        }

        val recyclerView = binding.listScore
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mainActivity = activity as MainActivity

        val listScore = getListScore()
        adapter.setData(listScore)
        recyclerView.adapter = adapter


        return view
    }

    private fun getEncryptedSharedPreferences(): SharedPreferences {
        val masterKeyAlias = MasterKey.Builder(requireContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        return EncryptedSharedPreferences.create(
            requireContext(),
            "secured_data_history_prefs",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getListScore(): MutableList<Score>{
        val gson = Gson()
        val json = getEncryptedSharedPreferences().getString("LIST",null)
        val type = object : TypeToken<MutableList<Score>>(){}.type //converting the json to list
        return gson.fromJson(json,type)
    }


}