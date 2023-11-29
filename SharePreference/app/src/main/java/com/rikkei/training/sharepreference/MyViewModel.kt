package com.rikkei.training.sharepreference

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class MyViewModel(private var name: String): BaseObservable() {

    @Bindable
    fun getName(): String = name

    fun setName(name: String){
        this.name = name
        notifyPropertyChanged(BR.name)
    }
}