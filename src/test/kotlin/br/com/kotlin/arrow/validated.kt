package br.com.kotlin.arrow

import arrow.core.*
import arrow.core.extensions.nonemptylist.semigroup.semigroup
import arrow.core.extensions.validated.applicative.applicative

fun main() {

    val result1 = ValidatedNel
        .applicative(Nel.semigroup<Error>())
        .map(
            validateGreaterThan3("Helena"),
            validateIsNumber("4")
        ) { (name, age) -> Person (name, age) }.fix()

    result1.fold(
        { errors -> errors.toList().forEach { println("error $it") } },
        { person -> println("Person valid: ${person}") }
    )

    println("--------")

    val result2 = ValidatedNel
        .applicative(Nel.semigroup<Error>())
        .map(
            validateGreaterThan3("Ju"),
            validateIsNumber("14")
        ) { (name, age) -> Person (name, age) }.fix()

    result2.fold(
        { errors -> errors.toList().forEach { println("error $it") } },
        { person -> println("Person valid: ${person}") }
    )

    println("--------")

    val result3 = ValidatedNel
        .applicative(Nel.semigroup<Error>())
        .map(
            validateGreaterThan3("Ju"),
            validateIsNumber("AA")
        ) { (name, age) -> Person (name, age) }.fix()

    result3.fold(
        { errors -> errors.toList().forEach { println("error $it") } },
        { person -> println("Person valid: ${person}") }
    )


}
data class Person(val name: String, val age: Int)
data class Error(val errorMessage: String)

fun validateGreaterThan3 (value: String): ValidatedNel<Error, String> =
    when (isGreaterThan3(value)) {
        true -> value.valid()
        else -> Error("I'm not greater than 3").invalid()
    }.toValidatedNel()

fun validateIsNumber(value: String): ValidatedNel<Error, Int> =
    when (isNumber(value)) {
        true -> value.toInt().valid()
        false -> Error("I'm not a number").invalid()
    }.toValidatedNel()


fun isGreaterThan3(value: String) = value.length > 3
fun isNumber(value: String) = value.toIntOrNull() != null

