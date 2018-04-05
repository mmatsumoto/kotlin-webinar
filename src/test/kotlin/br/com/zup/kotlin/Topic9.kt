package br.com.zup.kotlin

import org.junit.Test
import java.math.BigDecimal

class Topic9 {

    fun foobar1(a: Int): Int {

        val r =  if (a == 1) {
            println("${a.toLong()}")
            a * 2
        } else {
            -1
        }

        return r
    }

    fun foobar2(a: Int): Int {

//        if (a == 1) {
//            println("${a.toLong()}")
//            return a * 2
//        } else {
//            return -1
//        }

        return  if (a == 1) {
            println("${a.toLong()}")
            a * 2
        } else {
            -1
        }
    }

    fun foobar3(a: Int): Int =
            if (a == 1) {
                println("${a.toLong()}")
                a * 2
            } else {
                -1
            }


    fun foobar4(a: Int): Int {
        return try {
            println("${a.toLong()}")
            a * 2
        } catch(e: Exception) {
            -1
        }
    }

    @Test
    fun foobar5() {
        val numbers = (1..10).iterator()
        do {
            val i = numbers.nextInt()
            println("i $i is immutable")
        } while (numbers.hasNext())
    }

    fun foobar6() {

        val type = "something"
        var r = when (type) {
            "aaa" -> { 0 }
            "bbb" ->  1
            else  ->   -1
        }
        println(r)

        val a: Any = 1
        when (a) {
            is String -> a.toUpperCase()
            is Int -> {
                println("type Int")
                a.toBigDecimal()
            }
            1..10 -> println("range 1..10")
            else -> println("I quit!")
        }




        val aNull: Int? = null
        when (aNull) {
            null -> println("null value")
            else -> println("not null value")
        }



        val bd: BigDecimal? = BigDecimal.ONE
        when {
            bd != null -> { println("do something with not null") }
            else -> println("bd is null")
        }

    }

    fun foobar7(a: Int): Any {
        return try {
            println("${a.toLong()}")
            a * 2
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    fun foobar8(a: Int): Int {
        return try {
            println("${a.toLong()}")
            a * 2
        } catch(e: Exception) {
            fail(e)
        }
    }

}

fun fail(e: Exception): Nothing {
    e.printStackTrace()
    throw RuntimeException(e)
}