package com.rikkei.training.fragmentlifecycle

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.training.fragmentlifecycle.databinding.FragmentCBinding

private lateinit var binding: FragmentCBinding
class FragmentC : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCBinding.inflate(layoutInflater, container, false)
        val view = binding.root



        return view
    }

    fun receiverData(email: String){
        binding.txtEmail.text = email
    }

}