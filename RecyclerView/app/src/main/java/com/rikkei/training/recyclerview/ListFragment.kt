package com.rikkei.training.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkei.training.recyclerview.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = Adapter()
    private var listUser = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        createHeroData()
        val recyclerView = binding.recyclerView
//         LinearLayoutManager
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

//        // GridLayoutManager
//        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
//        layoutManager.spanSizeLookup = object : SpanSizeLookup(){
//            override fun getSpanSize(position: Int): Int {
//                return when(position % 3){
//                    0 -> 2
//                    else -> 1
//                }
//            }
//
//        }

//        // StaggerGridLayoutManager
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

        recyclerView.layoutManager = layoutManager
        adapter.setData(requireContext(), listUser)
        recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {

        }

        return view
    }

    private fun createHeroData(){

        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
        listUser.add(User("Minh", 21, "minh@gmail.com"))
        listUser.add(User("Manh", 23, "manh@gmail.com"))
        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
        listUser.add(User("Minh", 21, "minh@gmail.com"))
        listUser.add(User("Manh", 23, "manh@gmail.com"))

    }

}