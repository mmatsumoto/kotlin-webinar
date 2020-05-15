@file:Suppress("ThrowableNotThrown")

package br.com.kotlin.arrow

import arrow.core.*

import arrow.core.extensions.either.apply.tupled
import arrow.core.extensions.fx
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.lang.RuntimeException

fun main() {

    val e1: Either<Nothing, Int> = Either.right(1)
    val e2: Either<Int, Nothing> = Either.left(-1)

    val e3: Either<String, Int> = Either.right(2)
    when(e3) {
        is Either.Left -> println("I have left value ${e3.a}")
        is Either.Right -> println("I have right value ${e3.b}")
    }

    val e4: Either<String, Int> = Either.left("I have an error")
    when(e4) {
        is Either.Left -> println("I have left:  ${e4.a}")
        is Either.Right -> println("I have right: ${e4.b}")
    }

    val e5 = e3.flatMap { Either.right(it + 3) }
    println(e5)


    val e6: Either<Exception, Int> = Either.left(IllegalArgumentException("I have an error"))
    when(e6) {
        is Either.Left -> when (e6.a) {
            is NumberFormatException -> println("Error with NumberFormatException ${e6.a.message}")
            is IllegalArgumentException -> println("Error with IllegalArgumentException ${e6.a.message}")
            else -> println("another unmapped error")
        }
        is Either.Right -> println("I have right: ${e6.b}")
    }


    val e7: Either<NumberFormatException, Int> = 100.right()
    val e8: Either<Nothing, Int> = 100.right()


    val e9 = Either.cond(true, { 42 }, { "Error" })
    println(e9)

    println(100.right().fold({ -1 }, { it + 3 }))

    // Applicative
    val e10: Either<Nothing, Tuple3<Int, String, Double>> =
        tupled(Either.Right(1), Either.Right("a"), Either.Right(2.0))
    println(e10)

    val e11 = Either.fx<Int, Int> {
        val (a) = Either.Right(1)
        val (b) = Either.Right(1 + a)
        val (c) = Either.Right(1 + b)
        a + b + c
    }
    println(e11)

    val e12 = Either.fx<String, Int> {
        val (a) = Either.Right(1)
        val (e1 ) = Either.Left("Error 1")
        val (e2 ) = Either.Left("Error 2")
        val (c) = Either.Right(1 + a)
        a + c
    }
    println(e12)


}