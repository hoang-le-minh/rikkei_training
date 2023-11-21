package com.rikkei.training.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Data: ViewModel() {
    private var _listUser = MutableLiveData<MutableList<User>>()

    val listUser: LiveData<MutableList<User>>
        get() = _listUser

    init {
        val listUserTmp = mutableListOf(
            User("Hoang", 22, "hoang@gmail.com"),
            User("Minh", 21, "minh@gmail.com"),
            User("Manh", 23, "manh@gmail.com"),
            User("Hoang", 22, "hoang@gmail.com"),
            User("Minh", 21, "minh@gmail.com"),
            User("Manh", 23, "manh@gmail.com"),
            User("Hoang", 22, "hoang@gmail.com"),
            User("Minh", 21, "minh@gmail.com"),
            User("Manh", 23, "manh@gmail.com"),
            User("Hoang", 22, "hoang@gmail.com"),
            User("Minh", 21, "minh@gmail.com"),
            User("Manh", 23, "manh@gmail.com")
        )
        _listUser.postValue(listUserTmp)
    }

    fun addUser(user: User){
        _listUser.value?.add(user)
    }


}