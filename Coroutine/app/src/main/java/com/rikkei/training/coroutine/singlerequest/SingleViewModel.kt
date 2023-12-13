package com.rikkei.training.coroutine.singlerequest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rikkei.training.coroutine.base.BaseViewModel
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SingleViewModel: BaseViewModel() {

    var data = MutableLiveData<Int>()
    private set

    private lateinit var parentJob: Job

    override fun fetchData() {

        parentJob = viewModelScope.launch(parentExceptionHandler) {
            val deferred = async { repository.requestWithIndex(3, true) }
            val result = deferred.await()
            data.postValue(result)
        }

        parentJob.invokeOnCompletion {
            Log.d(LOG_TAG, "fetchData: Parent job finish")
        }

    }

    fun cancel(){
        parentJob.cancel()
    }
}