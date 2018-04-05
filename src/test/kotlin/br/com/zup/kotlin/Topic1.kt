package br.com.zup.kotlin

fun main(args: Array<String>) {
    println( "hello world")
    for (param in args) {
        println("param: $param")
    }
}


fun funSoloOne(a: Int): Unit {

}

fun funSoloTwo(b: Int): Int {
    return 1
}


class Topic1 {

    fun foobar(): String {
        return "something"
    }

}

class AnotherClass {

    fun foobar(): String {
        return "something"
    }

}
