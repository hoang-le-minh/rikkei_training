package com.rikkei.training.hiltapp.ui.stackexchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.training.hiltapp.MainActivity
import com.rikkei.training.hiltapp.R
import com.rikkei.training.hiltapp.databinding.FragmentStackExchangeBinding

class StackExchangeFragment : Fragment() {

    private lateinit var binding: FragmentStackExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStackExchangeBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Stack Exchange"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}