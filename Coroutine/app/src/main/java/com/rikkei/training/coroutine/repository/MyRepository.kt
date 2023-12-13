package com.rikkei.training.coroutine.repository

import android.util.Log
import com.rikkei.training.coroutine.home.LOG_TAG
import kotlinx.coroutines.*

class MyRepository(private  val dispatcher: CoroutineDispatcher) {

    suspend fun requestWithIndex(index: Int, isThrowException: Boolean = false): Int =
        withContext(dispatcher) {
            Log.d(LOG_TAG,"request with index $index at ${System.currentTimeMillis()} on thread ${Thread.currentThread().name}")
            val delayTime = index * 1000L
            delay(delayTime)
            if (isThrowException) {
                throw Exception("Exception with index $index")
            }
            Log.d(LOG_TAG,"Done with index $index")
            index
        }

}