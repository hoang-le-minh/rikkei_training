package com.rikkei.training.hiltapp.ui.jsonplaceholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rikkei.training.hiltapp.MainActivity
import com.rikkei.training.hiltapp.R
import com.rikkei.training.hiltapp.databinding.FragmentJsonPlaceHolderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JsonPlaceHolderFragment : Fragment() {

    private lateinit var binding: FragmentJsonPlaceHolderBinding
    private val jsonPlaceHolderViewModel by viewModels<JsonPlaceHolderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jsonPlaceHolderViewModel.getAllPosts()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJsonPlaceHolderBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Json Place Holder"

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = jsonPlaceHolderViewModel

        return binding.root
    }


}