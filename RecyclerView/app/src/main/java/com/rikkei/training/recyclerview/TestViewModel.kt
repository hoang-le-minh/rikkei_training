package com.rikkei.training.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel: ViewModel() {

    private val _clickCount = MutableLiveData<Int>()
    var number = 0

    val clickCount: LiveData<Int>
        get() = _clickCount

    fun incrementClickCount() {
        _clickCount.postValue(++number)
    }

    override fun onCleared() {
        super.onCleared()
    }
}