package br.com.kotlin.arrow

import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.fx.fix

fun main() {

    val ioI =
    IO.fx {
        1
    }.unsafeRunSync()

}