package com.rikkei.training.sharepreference

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rikkei.training.sharepreference.databinding.CustomLayoutBinding
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class UserListAdapter: ListAdapter<User, UserListAdapter.MyViewHolder>(
    AsyncDifferConfig.Builder(UserDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    class MyViewHolder(private val binding: CustomLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(user: User) {
            binding.userViewModel = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.inflate<CustomLayoutBinding>(layoutInflater, viewType, parent, false)
        val binding = CustomLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }

    override fun submitList(list: MutableList<User>?) {
        super.submitList(list)
    }

//    override fun getItemViewType(position: Int): Int {
//        return R.layout.custom_layout
//    }
}

class UserDiffCallBack: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.getName() == newItem.getName()
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

}