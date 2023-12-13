package com.rikkei.training.coroutine.singlerequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rikkei.training.coroutine.R
import com.rikkei.training.coroutine.databinding.FragmentSingleBinding

class SingleFragment : Fragment() {

    private lateinit var binding: FragmentSingleBinding
    private val singleViewModel by viewModels<SingleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        singleViewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleViewModel.data.observe(viewLifecycleOwner){
            binding.txtResult.text = "Result: index $it"
        }
        singleViewModel.error.observe(viewLifecycleOwner){
            binding.txtResult.text = it
        }

        binding.btnCancel.setOnClickListener {
            singleViewModel.cancel()
        }
    }

}