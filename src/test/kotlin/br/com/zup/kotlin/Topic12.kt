package br.com.zup.kotlin

import org.junit.Test
import kotlin.test.assertEquals


val myFunc1: ((Int, Int) -> Int)? = null


val myFunc2: (x: Int, y: Int) -> Int = { x, y -> x * y }

val doubleIt: (x: Int) -> Int = { it * it }




//fun hightOrderFunction(age: Int, myFunc: (Int, String) -> Int): Int {
fun hightOrderFunction(age: Int = 0,
                       myFunc: (age: Int) -> Int): Int {
    return myFunc(age)
}


fun hightOrderFunction2(age: Int = 0,
                        name: String = "default",
                        myFunc: (age: Int, name: String) -> Int): Int {
    return myFunc(age, name)
}

fun fnReturningLambda(x: Int): (x:Int) -> Int {
    return { n -> n * x }
}


class Topic12 {

    fun test1() {
        hightOrderFunction(10,
                           { age -> age * 2 })
    }

    fun test2() {
        hightOrderFunction(10, { it * 2 })
    }

    fun test3() {


        hightOrderFunction(10) {
            it * 2
        }
    }

    fun test4() {
        hightOrderFunction { it * 2 }
    }

    fun test5() {
        hightOrderFunction2 { age, name -> age * 2 }


        hightOrderFunction2 { age, _ -> age * 2 }
    }

    @Test
    fun testMyForeach(): Unit {

        println(myForeach())

//        assertEquals(-1, myForeach())
//        assertEquals(2, myForeach())
    }

    @Test
    fun testingReturningLambda() {
        val x = fnReturningLambda(2).invoke(2)
        assertEquals(4, x)
    }


}


val isOdd: (x:Int) -> Boolean = { it % 2 != 0 }

fun print(x:Int) { println("number: $x") }

fun functioReferences(args: Array<String>) {

    // modelo 1
    listOf(1, 2, 3, 4)
            .filter { isOdd(it) }
            .forEach { print(it) }

    // modelo 2
    listOf(1, 2, 3, 4)
            .filter(isOdd)
            .forEach(::print)

}



fun myForeach(): Int {
    (1..100).myForeach {
        if (it % 2 == 0)
            return it
    }
    return -1
}


inline fun <T> Iterable<T>.myForeach(f: (T) -> Unit) {
    for (e in this) f(e)
}

