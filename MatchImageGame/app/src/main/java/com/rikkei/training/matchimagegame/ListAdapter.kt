package com.rikkei.training.matchimagegame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    private var scoreList = emptyList<Score>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_score_item, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = scoreList[position]
        holder.itemView.findViewById<TextView>(R.id.time).text = currentItem.timestamp
        holder.itemView.findViewById<TextView>(R.id.score).text = currentItem.score.toString()
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    fun setData(score: List<Score>){
        this.scoreList = score

    }
}