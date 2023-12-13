package com.rikkei.training.coroutine.multiplerequest.sequential

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rikkei.training.coroutine.base.BaseViewModel
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.launch

class SequentialViewModel: BaseViewModel() {

    override fun fetchData() {

        val parentJob = viewModelScope.launch(parentExceptionHandler) {
            Log.d(LOG_TAG, "fetchData: loading...")
            repository.requestWithIndex(1, false)
            repository.requestWithIndex(2, true)
        }

        parentJob.invokeOnCompletion {
            Log.d(LOG_TAG, "fetchData: finish")
        }

    }
}