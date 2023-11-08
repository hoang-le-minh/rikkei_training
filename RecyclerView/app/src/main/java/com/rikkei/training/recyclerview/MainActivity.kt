package com.rikkei.training.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rikkei.training.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = Adapter()
    private var listHero = mutableListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        createHeroData()
        val recyclerView = binding.recyclerView
//         LinearLayoutManager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
        adapter.setData(this, listHero)
        recyclerView.adapter = adapter

    }

    private fun createHeroData(){
        listHero.add(Hero("Thor", R.drawable.thor))
        listHero.add(Hero("Hulk", R.drawable.hulk))
        listHero.add(Hero("IronMan", R.drawable.ironman))
        listHero.add(Hero("SpiderMan", R.drawable.spiderman))
        listHero.add(Hero("Thor", R.drawable.thor))
        listHero.add(Hero("Hulk", R.drawable.hulk))
        listHero.add(Hero("IronMan", R.drawable.ironman))
        listHero.add(Hero("SpiderMan", R.drawable.spiderman))

    }
}