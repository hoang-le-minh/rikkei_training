package com.rikkei.training.sharepreference

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide

class Activity2ViewModel(val content: ObservableField<String>) {

    val imageUrl = "https://cdn0.fahasa.com/media/catalog/product/n/o/nong-gian-la-ban-nang-1_1.jpg"

    fun changeValueContent(){
        content.set("New Content")
    }

}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Glide.with(view.context).load(url).error(error).into(view)
}