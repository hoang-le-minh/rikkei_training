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
    private var listUser = emptyList<User>()
    private lateinit var context: Context

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
        val txtAge: TextView = itemView.findViewById(R.id.txt_age)
        val txtEmail: TextView = itemView.findViewById(R.id.txt_email)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = listUser[position]
        val ageString = "(${currentUser.age})"
        holder.txtName.text = currentUser.name
        holder.txtAge.text = ageString
        holder.txtEmail.text = currentUser.email

    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun setData(context: Context, listUser: MutableList<User>){
        this.listUser = listUser
        this.context = context
        notifyDataSetChanged()
    }


}