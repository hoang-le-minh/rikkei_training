package com.rikkei.training.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.training.fragmentlifecycle.databinding.FragmentABinding

private lateinit var binding: FragmentABinding
class FragmentA : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding.root

        mainActivity = activity as MainActivity

        binding.edtEmailA.setText(arguments?.getString("emailA"))

        binding.btnUpdateA.setOnClickListener {
            sendDataToActivity()
        }

        return view
    }

    private fun sendDataToActivity() {
        val email = binding.edtEmailA.text.toString()
        mainActivity.getEdtEmail().setText(email)
    }

}