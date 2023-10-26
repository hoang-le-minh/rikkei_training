package com.rikkei.training.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.rikkei.training.navigation.databinding.FragmentFirstBinding

private lateinit var binding: FragmentFirstBinding
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.txtFirst.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(23)
            view.findNavController().navigate(action)
        }

        return view
    }

}