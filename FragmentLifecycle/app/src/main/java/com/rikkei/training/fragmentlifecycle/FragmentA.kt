package com.rikkei.training.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
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

        binding.btnNavToB.setOnClickListener {
            navToFragmentB()
        }

        binding.btnSendToC.setOnClickListener {
            sendDataToC()
        }

        return view
    }

    private fun sendDataToC() {
        val email = binding.edtEmailA.text.toString()
        val fragmentC = mainActivity.supportFragmentManager.findFragmentById(R.id.frm_layout2) as? FragmentC
        fragmentC?.receiverData(email)
    }

    private fun sendDataToActivity() {
        val email = binding.edtEmailA.text.toString()
        mainActivity.getEdtEmail().setText(email)
    }

    private fun navToFragmentB(){

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentAA, FragmentB())
        fragmentTransaction.commit()
    }


}