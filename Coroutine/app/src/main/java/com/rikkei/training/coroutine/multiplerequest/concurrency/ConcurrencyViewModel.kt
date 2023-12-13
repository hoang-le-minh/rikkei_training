package com.rikkei.training.coroutine.multiplerequest.concurrency

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rikkei.training.coroutine.base.BaseViewModel
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.launch

class ConcurrencyViewModel: BaseViewModel() {

    override fun fetchData() {
        val parentJob = viewModelScope.launch(parentExceptionHandler) {

            launch { repository.requestWithIndex(1) }
            launch { repository.requestWithIndex(2) }

        }
        parentJob.invokeOnCompletion {
            Log.d(LOG_TAG, "fetchData: Finish")
        }
    }
}