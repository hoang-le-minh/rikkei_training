package com.rikkei.training.musicapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.rikkei.training.musicapp.R
import com.rikkei.training.musicapp.databinding.FragmentViewPagerBinding
import com.rikkei.training.musicapp.fragment.ListAlbumFragment
import com.rikkei.training.musicapp.fragment.ListArtistFragment
import com.rikkei.training.musicapp.fragment.ListSongFragment

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val fragmentList = listOf(
            ListSongFragment(),
            ListAlbumFragment(),
            ListArtistFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)

        binding.viewPager.adapter = adapter

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> "Songs"
                1 -> "Albums"
                2 -> "Artists"
                else -> "Unknown"
            }
        }.attach()

        return view
    }

}