package br.com.kotlin.arrow

import arrow.core.extensions.list.semigroupK.combineK

fun main() {


    val hello = listOf('h', 'e', 'l', 'l', 'o')
    val dash = listOf('-')
    val world = listOf('w', 'o', 'r', 'l', 'd')

    val r = hello
        .combineK(dash)
        .combineK(world)


    println("r : $r")
    println("r : ${r.size}")

}