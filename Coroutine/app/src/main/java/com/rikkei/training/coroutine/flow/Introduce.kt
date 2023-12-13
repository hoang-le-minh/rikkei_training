package com.rikkei.training.coroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main(){
    runBlocking {

        /*launch {
            println(Thread.currentThread().name)
            for (i in 1..3){
                delay(1000)
                println("Not Blocked $i")
            }
        }
        foo(3).collect{
            println("i = $it")
        }*/

        /*withTimeoutOrNull(5000){
            foo(3).collect{
                println("i = $it")
            }
        }
        println("Done")*/

        (1..5).asFlow().collect{
            delay(1000)
            println("i = $it")
        }

    }
}

fun foo(x: Int): Flow<Int> = flow{
    for(i in 1..x){
        delay(2000)
//        Thread.sleep(2000)
        emit(i)
    }
}