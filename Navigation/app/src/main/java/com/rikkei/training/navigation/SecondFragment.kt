package com.rikkei.training.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.rikkei.training.navigation.databinding.FragmentSecondBinding

private lateinit var binding: FragmentSecondBinding
class SecondFragment : Fragment() {

    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val myNumber = args.number
        binding.txtSecond.text = myNumber.toString()

        binding.txtSecond.setOnClickListener{
            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            view.findNavController().navigate(action)
        }

        return view
    }

}