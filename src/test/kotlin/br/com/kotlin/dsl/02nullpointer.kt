package br.com.kotlin.dsl

fun main() {

    val foo1: String = "bar"


    val foo2: String? = null

    val s = foo2?.toUpperCase() ?: "orelsevalue"




}