package com.rikkei.training.coroutine.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rikkei.training.coroutine.CoroutineScopeActivity
import com.rikkei.training.coroutine.R
import com.rikkei.training.coroutine.databinding.FragmentHomeBinding
import com.rikkei.training.coroutine.detail.DetailFragment
import com.rikkei.training.coroutine.multiplerequest.concurrency.ConcurrencyFragment
import com.rikkei.training.coroutine.multiplerequest.sequential.SequentialFragment
import com.rikkei.training.coroutine.singlerequest.SingleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        (activity as CoroutineScopeActivity).supportActionBar?.title = "Home"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            homeViewModel.start()
        }

        binding.btnStop.setOnClickListener {
            homeViewModel.stop()
        }

        binding.btnViewDetail.setOnClickListener {
            replaceFragment(DetailFragment())
        }

        binding.btnSingleRequest.setOnClickListener {
            replaceFragment(SingleFragment())
        }
        binding.btnMultipleRequestConcurrency.setOnClickListener {
            replaceFragment(ConcurrencyFragment())
        }
        binding.btnMultipleRequestSequential.setOnClickListener {
            replaceFragment(SequentialFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val activity = activity as CoroutineScopeActivity
        activity.supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).addToBackStack("home").commit()
    }


}