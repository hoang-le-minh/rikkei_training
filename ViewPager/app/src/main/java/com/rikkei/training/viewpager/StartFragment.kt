package com.rikkei.training.viewpager

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.rikkei.training.viewpager.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed({
            view.findNavController().navigate(R.id.action_startFragment_to_viewPagerFragment)
        }, 2000)

        return view
    }

}