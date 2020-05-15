package br.com.kotlin.dsl

fun main() {

    val foo1: String = "bar"

    // ?

    val foo2: String? = null

//    foo2.toUpperCase()
    if (foo2 != null) {
        foo2.toUpperCase()
    }

    foo2?.toUpperCase()

    // ?:
    val foo3 = foo2?.toUpperCase() ?: "orsomething"

    // !!

}