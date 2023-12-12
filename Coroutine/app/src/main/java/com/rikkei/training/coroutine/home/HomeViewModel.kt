package com.rikkei.training.coroutine.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

const val LOG_TAG = "hoangminhdh11"
class HomeViewModel: ViewModel() {

    private var job: Job? = null
    var result = 0

    fun start(){
        val parentCoroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            Log.d(LOG_TAG, "start:${throwable.message}")
        }
        job = viewModelScope.launch(parentCoroutineExceptionHandler) {

            supervisorScope {
                val child1Exception = CoroutineExceptionHandler{_, throwable ->
                    Log.d(LOG_TAG, "Child 1:${throwable.message}")
                }
                launch(Dispatchers.IO + child1Exception) {
                    getWithIndex(1, true)
                }

                val child2Exception = CoroutineExceptionHandler{_, throwable ->
                    Log.d(LOG_TAG, "Child 2:${throwable.message}")
                }
                launch(Dispatchers.IO + child2Exception) {
                    getWithIndex(2, true)
                }
            }

//            getDataFromServer()
        }

        job?.invokeOnCompletion {
            Log.d(LOG_TAG, "start: job finish")
        }
    }

    fun stop(){
        job?.cancel()
    }

    private suspend fun getDataFromServer(){
        Log.d(LOG_TAG, "getDataFromServer: getting")
        delay(2000)
        Log.d(LOG_TAG, "getDataFromServer: got")
    }

    private suspend fun getWithIndex(index: Int, isThrowException: Boolean = false): Int {
        Log.d(LOG_TAG, "getWithIndex: $index, start at: ${System.currentTimeMillis()} in ${Thread.currentThread()}")
        val delayTime = index*1000L
        delay(delayTime)
        if(isThrowException){
            throw Exception("Exception for index: $index")
        }
        Log.d(LOG_TAG, "getWithIndex: result $index, finish at: ${System.currentTimeMillis()}")
        return index
    }
}