package br.com.kotlin.arrow

import arrow.core.extensions.list.semigroupK.*

fun main() {


//    ListK.semigroup<Int>().combine()

    val list1 = listOf(1,2,3)
    val list2 = listOf(5,6,7)


    val list3 = list1.combineK(list2)
    println(list3)
    list3.forEach {
        println(it)
    }

    val list4 = listOf("a", "b", "c")

    val list5 = list1.combineK(list4)
    println(list5)
    list5.forEach {
        println(it)
    }


}