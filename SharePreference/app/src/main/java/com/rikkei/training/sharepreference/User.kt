package com.rikkei.training.sharepreference

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

data class User(private var name: String, private var age: Int): BaseObservable() {

    @Bindable
    fun getName(): String = name

    @Bindable
    fun getAge(): Int = age

    fun changeInfoUser() {
        Log.d("hoangminhdh11", "showLogInfo: $name ($age)")
        name = name.dropLast(1)
        ++age
        notifyPropertyChanged(BR.name)
        notifyPropertyChanged(BR.age)
    }

}