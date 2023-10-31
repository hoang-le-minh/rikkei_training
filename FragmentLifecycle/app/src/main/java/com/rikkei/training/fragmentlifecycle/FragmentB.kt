package com.rikkei.training.fragmentlifecycle

import android.os.Bundle
import android.util.Log
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

    var onChangFragment: OnChangFragment? = null
    set(value) {
        field = value
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle", "Fragment B: onCreateView")
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
        onChangFragment?.changeA()
//        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragmentB, FragmentA())
//        fragmentTransaction.commit()
    }

    private fun sendDataToActivity() {
        val email = binding.edtEmailB.text.toString()
        mainActivity.getEdtEmail().setText(email)
    }

}

interface OnChangFragment{
    fun changeA()
}