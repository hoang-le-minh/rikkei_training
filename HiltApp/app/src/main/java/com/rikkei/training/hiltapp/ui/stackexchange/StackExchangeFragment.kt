package com.rikkei.training.hiltapp.ui.stackexchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rikkei.training.hiltapp.MainActivity
import com.rikkei.training.hiltapp.R
import com.rikkei.training.hiltapp.databinding.FragmentStackExchangeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StackExchangeFragment : Fragment() {

    private lateinit var binding: FragmentStackExchangeBinding
    private val stackExchangeViewModel by viewModels<StackExchangeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackExchangeViewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStackExchangeBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Stack Exchange"

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = stackExchangeViewModel

        stackExchangeViewModel.listQuestions.observe(viewLifecycleOwner){ list ->
            list.firstOrNull()?.let { question ->
                binding.txtResult.text = question.toString()
            }
        }

        return binding.root
    }


}