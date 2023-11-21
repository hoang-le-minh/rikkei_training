package com.rikkei.training.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class UserListAdapter : ListAdapter<User, UserListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(UserDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var txtName: TextView = itemView.findViewById(R.id.txt_name)
        var txtAge: TextView = itemView.findViewById(R.id.txt_age)
        var txtEmail: TextView = itemView.findViewById(R.id.txt_email)

        fun onBind(user: User){
            txtName.text = user.name
            txtAge.text = user.age.toString()
            txtEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun submitList(list: MutableList<User>?) {
        super.submitList(list ?: listOf())
    }

    override fun getItem(position: Int): User {
        return super.getItem(position)
    }
}

class UserDiffCallBack: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

}