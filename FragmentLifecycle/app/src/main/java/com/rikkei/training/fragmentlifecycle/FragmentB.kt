package com.rikkei.training.fragmentlifecycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.rikkei.training.fragmentlifecycle.databinding.FragmentBBinding

private lateinit var binding: FragmentBBinding
class FragmentB : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBBinding.inflate(inflater, container, false)
        val view = binding.root

        mainActivity = activity as MainActivity

        binding.edtEmailB.setText(arguments?.getString("emailB"))

        binding.btnUpdateB.setOnClickListener {
            sendDataToActivity()
        }

        binding.btnNavToA.setOnClickListener {
            navToFragmentA()
        }

        return view
    }

    private fun navToFragmentA() {

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentBB, FragmentA())
        fragmentTransaction.commit()
    }

    private fun sendDataToActivity() {
        val email = binding.edtEmailB.text.toString()
        mainActivity.getEdtEmail().setText(email)
    }


}