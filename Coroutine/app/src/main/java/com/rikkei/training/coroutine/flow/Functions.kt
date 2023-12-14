package com.rikkei.training.coroutine.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
fun main(){

    runBlocking {
        // transform
        /*(1..5).asFlow().transform { value ->
            if(value % 2 == 0){
                emit(value*2)
                emit(value*3)
            }
        }.collect{ response ->
            delay(500)
            println("i = $response")
        }*/

        // map
        /*(1..5).asFlow().map {
            it*3
        }.collect{ response ->
            delay(500)
            println("i = $response")
        }*/

        // take
        /*(1..5).asFlow().take(3).collect{
            delay(500)
            println("i = $it")
        }

        fun numbers(): Flow<Int> = flow {
            emit(4)
            emit(5)
            println("asdfasdf")
            emit(6)
        }

        numbers().take(2).collect{
            delay(500)
            println("i = $it")
        }*/

        // filter
        /*(1..9).asFlow().filter {
            println("Filter $it")
            delay(500)
            it % 2 == 0
        }.collect{ response ->
            println("i = $response")
        }*/

        // reduce
        /*(1..5).asFlow().reduce{ prevResponse, value ->
            delay(500)
            println("prevResponse: $prevResponse, value: $value")
            prevResponse + value
        }*/

        // fold
        /*(1..5).asFlow().fold(10){ prevResponse, value ->
            delay(500)
            println("prevResponse: $prevResponse, value: $value")
            prevResponse + value
        }*/

        // single, singleOrNull
        /*try {
            val a = listOf(5).asFlow().single()
            println("a = $a")

//            listOf(1, 2).asFlow().single()
//            listOf<Int>().asFlow().single()
            val b = listOf<Int>().asFlow().singleOrNull()
            println("b = $b")
            val c = listOf(1, 2).asFlow().singleOrNull()
            println("c = $c")
        } catch (e: Exception){
            e.printStackTrace()
        }*/

        // zip
        val nums = (1..5).asFlow().onEach { delay(100) }
        val strs = flowOf("one", "two", "three", 4).onEach { delay(200) }
        /*nums.zip(strs){num, str -> "$num -> $str"}
            .collect{
                delay(500)
                println(it)
            }*/

        // combine
        /*nums.combine(strs){ num, str -> "$num -> $str"}
            .collect{
                println(it)
            }*/

        val startTime = System.currentTimeMillis()
        // flatMapConcat
        /*(1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { requestFlow(it) }
            .collect{ response ->
                println("$response at ${System.currentTimeMillis() - startTime}ms")
            }*/

        // flatMapMerge
        /*(1..3).asFlow().onEach { delay(100) }
            .flatMapMerge { requestFlow(it) }
            .collect{ response ->
                println("$response at ${System.currentTimeMillis() - startTime}ms")
            }*/

        // flatMapLatest

        (1..3).asFlow().onEach { delay(100) }
            .flatMapLatest { requestFlow(it) }
            .collect{ response ->
                println("$response at ${System.currentTimeMillis() - startTime}ms")
            }
    }

}

fun requestFlow(i: Int): Flow<String> = flow { // Đây là flowB
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}