package com.rikkei.training.coroutine.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rikkei.training.coroutine.home.LOG_TAG
import com.rikkei.training.coroutine.repository.MyRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

open class BaseViewModel: ViewModel() {

    protected var repository: MyRepository = MyRepository(Dispatchers.IO)

    var error = MutableLiveData<String>()
    protected set

    protected val parentExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        error.postValue(throwable.message)
        Log.d(LOG_TAG, "Exception: ${throwable.message}")
    }

    open fun fetchData() {


    }
}