package com.rikkei.training.coroutine.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*fun foo() = flow<Int> {
        log("Start foo flow")
        for (i in 1..3) emit(i)
    }

    foo().collect{
        log("i = $it")
    }*/

    // flowOn
    /*fun foo2() = flow {
        log("[${Thread.currentThread().name}] Start foo flow")
        for (i in 1..3) emit(i)
    }.flowOn(Dispatchers.Default)

    foo2().collect{
        log("i = $it")
    }*/

    // flow exception
    /*fun foo3(): Flow<Int> = flow {
        for(i in 3 downTo -1) emit(i)
    }
    try {
        foo3().collect{
            println("3/$it = ${3/it}")
        }
    } catch (e: Exception){
        println(e)
    }*/

    // catch
    /*fun foo4() = flow {
        for (i in 3 downTo -1){
            emit(i.toString())
            println("3/$i = ${3/i}")
        }
    }
    foo4().catch { e -> emit("Caught $e") }
        .collect{
            println("value = $it")
        }*/

    // launchIn
    /*(1..3).asFlow()
        .onEach { value ->
            delay(200)
            println("value = $value")
        }
        .launchIn(this)
    println("========launchIn=======")*/

    // onCompletion
    (1..3).asFlow()
        .onCompletion { println("Flow Done!") }
        .collect{
            delay(200)
            println("i = $it")
        }
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")