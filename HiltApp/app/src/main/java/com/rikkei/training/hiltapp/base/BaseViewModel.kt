package com.rikkei.training.hiltapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

open class BaseViewModel: ViewModel() {

    protected var parentJob: Job? = null

    var isLoading = MutableLiveData(false)
    private set

    protected fun registerEventParentJobFinish(){
        parentJob?.invokeOnCompletion {
            isLoading.postValue(false)

        }
    }

}