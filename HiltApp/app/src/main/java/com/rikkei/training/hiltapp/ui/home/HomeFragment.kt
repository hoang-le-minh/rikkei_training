package com.rikkei.training.hiltapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.training.hiltapp.MainActivity
import com.rikkei.training.hiltapp.R
import com.rikkei.training.hiltapp.databinding.FragmentHomeBinding
import com.rikkei.training.hiltapp.ui.jsonplaceholder.JsonPlaceHolderFragment
import com.rikkei.training.hiltapp.ui.stackexchange.StackExchangeFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Home"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnJsonPlaceHolder.setOnClickListener {
                replaceFragment(JsonPlaceHolderFragment())
            }
            btnStackExchange.setOnClickListener {
                replaceFragment(StackExchangeFragment())

            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("home")
            .commit()
    }


}