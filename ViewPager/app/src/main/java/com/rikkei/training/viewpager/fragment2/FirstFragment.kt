package com.rikkei.training.viewpager.fragment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rikkei.training.viewpager.Activity2
import com.rikkei.training.viewpager.R
import com.rikkei.training.viewpager.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        val activity2 = activity as Activity2
        activity2.supportActionBar?.title = "First Fragment"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_homeFragment2)
            findNavController().popBackStack()
        }
    }


}