package com.rikkei.training.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rikkei.training.recyclerview.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var testViewModel: TestViewModel
    private lateinit var data: Data

    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val mainActivity  = activity as MainActivity

        testViewModel = ViewModelProvider(mainActivity)[TestViewModel::class.java]
        data = ViewModelProvider(mainActivity)[Data::class.java]
        testViewModel.clickCount.observe(viewLifecycleOwner){
            binding.textCount.text = it.toString()
        }

        binding.btnAdd.setOnClickListener {
            addUser()
            findNavController().popBackStack()
        }

        return view
    }

    private fun addUser(){
        var name = binding.edtAddName.text.toString()
        var age = binding.edtAddAge.text.toString()
        val email = binding.edtAddEmail.text.toString()

        if(name.trim() == ""){
            name = "Unknown"
        }
        if(age == ""){
            age = "0"
        }

        val user = User(name, age.toInt(), email)
        data.addUser(user)

    }


}