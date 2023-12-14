package com.rikkei.training.hiltapp.ui.jsonplaceholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.training.hiltapp.MainActivity
import com.rikkei.training.hiltapp.R
import com.rikkei.training.hiltapp.databinding.FragmentJsonPlaceHolderBinding

class JsonPlaceHolderFragment : Fragment() {

    private lateinit var binding: FragmentJsonPlaceHolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJsonPlaceHolderBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Json Place Holder"
        return binding.root
    }


}