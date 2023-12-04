package com.rikkei.training.viewpager.fragment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.rikkei.training.viewpager.Activity2
import com.rikkei.training.viewpager.R
import com.rikkei.training.viewpager.databinding.FragmentHome2Binding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHome2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHome2Binding.inflate(layoutInflater, container, false)
        val activity2 = activity as Activity2
        activity2.supportActionBar?.title = "Home Fragment"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFirst.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_firstFragment)
        }

        binding.btnSecond.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_secondFragment)
        }

        binding.btnOpenBottomDialogFragment.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bottomSheetDialogFragment)
        }
    }


}