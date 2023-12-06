package com.rikkei.training.viewpager.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rikkei.training.viewpager.R

class PagerAdapter: RecyclerView.Adapter<PagerAdapter.MyViewHolder>() {
    private var listImage = emptyList<Int>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun onBind(resource: Int){
            imageView.setImageResource(resource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pager_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(listImage[position])
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    fun setData(listImage: MutableList<Int>){
        this.listImage = listImage
        notifyDataSetChanged()
    }
}