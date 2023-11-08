package com.rikkei.training.recyclerview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.coroutines.coroutineContext

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var listHero = emptyList<Hero>()
    private lateinit var context: Context

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageHero = itemView.findViewById<ImageView>(R.id.image_hero)
        val txtName = itemView.findViewById<TextView>(R.id.text_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentHero = listHero[position]
        holder.txtName.text = currentHero.name

        Glide.with(context)
            .load(currentHero.image)
            .into(holder.imageHero)

    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    fun setData(context: Context, listHero: MutableList<Hero>){
        this.listHero = listHero
        this.context = context
    }


}