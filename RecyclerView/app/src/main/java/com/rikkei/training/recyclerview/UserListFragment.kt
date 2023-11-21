package com.rikkei.training.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkei.training.recyclerview.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
//    private var listUser = mutableListOf<User>()
    private val listAdapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_userListFragment_to_addFragment2)
        }
        val mainActivity = activity as MainActivity
        val data = ViewModelProvider(mainActivity)[Data::class.java]
        val recyclerView = binding.recyclerView2
//        createData()
        data.listUser.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = listAdapter


        return view
    }

//    private fun createData(){
//        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
//        listUser.add(User("Minh", 21, "minh@gmail.com"))
//        listUser.add(User("Manh", 23, "manh@gmail.com"))
//        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
//        listUser.add(User("Minh", 21, "minh@gmail.com"))
//        listUser.add(User("Manh", 23, "manh@gmail.com"))
//        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
//        listUser.add(User("Minh", 21, "minh@gmail.com"))
//        listUser.add(User("Manh", 23, "manh@gmail.com"))
//        listUser.add(User("Hoang", 22, "hoang@gmail.com"))
//        listUser.add(User("Minh", 21, "minh@gmail.com"))
//        listUser.add(User("Manh", 23, "manh@gmail.com"))
//    }

}