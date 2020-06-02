package br.com.kotlin.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun foo(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() {
    println("Calling foo...")
    val flow = foo()
    println("Calling collect...")

    runBlocking<Unit> {
        flow.collect { value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }

        (1..3).asFlow()
                .map { it * 2 }
                .collect {
                    println("value: $it")
                }


        flow
                .onEach { value ->
                    check(value <= 1) { "Collected $value" }
                    println(value)
                }
                .catch { e -> println("Caught $e") }
                .collect()
    }
}