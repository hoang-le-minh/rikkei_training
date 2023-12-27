package com.rikkei.training.coroutine.multiplerequest.concurrency

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rikkei.training.coroutine.base.BaseViewModel
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.*

class ConcurrencyViewModel : BaseViewModel() {

    override fun fetchData() {
        GlobalScope.launch {

        }
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                delay(100)
                Log.d(
                    LOG_TAG,
                    "request with index 1"
                )
            }

            launch {
                Log.d(
                    LOG_TAG,
                    "request with index 2"
                )
            }

            launch {
                Log.d(
                    LOG_TAG,
                    "request with index 2"
                )
            }
        }
        val parentJob = viewModelScope.launch(parentExceptionHandler) {
            repository.requestWithIndex(1, true)
            repository.requestWithIndex(2)
//            supervisorScope {
//                launch { repository.requestWithIndex(1, true) }
//                launch { repository.requestWithIndex(2) }
//            }


        }
        parentJob.invokeOnCompletion {
            Log.d(LOG_TAG, "fetchData: Finish")
        }
    }
}