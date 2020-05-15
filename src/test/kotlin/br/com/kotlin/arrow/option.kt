package br.com.kotlin.arrow

import arrow.core.*

import arrow.core.extensions.option.apply.tupled
import arrow.core.extensions.fx
import arrow.core.extensions.option.apply.map
import arrow.typeclasses.*
import arrow.core.extensions.*
import arrow.core.extensions.option.monad.*

fun main() {


    val o1Int: Option<Int> = Option(1)
    val o2Int: Option<Int> = Option.empty()
    val o3Int = Option.just(3)

    o1Int.fold({ println("o1 is empty") }, { println("o1 has value: $it") })
    o2Int.fold({ println("o2 is empty") }, { println("o2 has value: $it") })
    o3Int.fold({ println("o3 is empty") }, { println("o3 has value: $it") })

    println(o2Int.orElse { Option.just(-1) })
    println(o2Int.getOrElse { -1 })

    println(o1Int to 2)
    //Functor
    println(o1Int.map { 1 + 10 })

    when (o1Int) {
        is Some -> println("I have something for you ${o1Int.t}")
        is None -> println("I'm empty")
    }

    when (o2Int) {
        is Some -> println("I have something for you ${o2Int.t}")
        is None -> println("I'm empty")
    }


    // Applicative
    val o1Applicative: Option<Tuple3<Int, Int, Int>>
            = tupled(Option.just(1), Option.just(2), Option.just(3))
    println(o1Applicative)


    val o2Applicative: Option<Tuple2<Int, Int>>
            = tupled(o1Int, o2Int)
    println(o2Applicative)

    println("Monad: ")
    // Monad
    val o1Monad = Option.fx {
        val (a) = Some(1)
        val (b) = Some(1 + a)
        val (c) = Some(1 + b)
        a + b + c
    }
    println(o1Monad)

    val o2Monad = Option.fx {
        val (a) = Some(1)
        val (b) = Option.empty<Int>()
        val (c) = Some(1 + b)
        a + b + c
    }
    println(o2Monad)

    println("--- Map over Some(s)")
    map(Some(1), Some(2), Some("im a text")) {
        (intA, intB, textC) ->
            println("I have a triple: ($intA, $intB, '$textC')")
    }

    val oNone: Option<Int> = None
    map(Option(1), oNone, Option("im a text")) {
        (intA, intB, textC) ->
        println("Never will be executed because intB is None")
    }
    println("--------")





    val e1: Either<String, Int> = o1Int.toEither { "I'm empty" }
    println(e1)

    val e2: Either<String, Int> = o2Int.toEither { "I'm empty" }
    println(e2)

    val e3: Either<Int, Int> = o2Int.toEither { -1 }
    println(e3)

}