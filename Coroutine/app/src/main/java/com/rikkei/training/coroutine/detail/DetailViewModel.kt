package com.rikkei.training.coroutine.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.*

class DetailViewModel: ViewModel() {

    private var job: Job? = null

    fun getData(){
        job = viewModelScope.launch {
            downloadAndSave()
        }
        job?.invokeOnCompletion {
            Log.d(LOG_TAG, "getData: job finish")
        }
    }

    private suspend fun downloadAndSave() = withContext(Dispatchers.IO){
        Log.d(LOG_TAG, "downloadAndSave: start")
        delay(2000)
        Log.d(LOG_TAG, "downloadAndSave: get data from server")
        saveToDB()
    }
    
    private suspend fun saveToDB() = withContext(NonCancellable){
        delay(2000)
        Log.d(LOG_TAG, "saveToDB: saved")
    }
}