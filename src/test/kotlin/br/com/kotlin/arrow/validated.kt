package br.com.kotlin.arrow

import arrow.core.*
import arrow.core.extensions.nonemptylist.semigroup.semigroup
import arrow.core.extensions.validated.applicative.applicative
import arrow.core.extensions.validated.semigroupK.semigroupK

fun main() {

    val result1 = ValidatedNel
        .applicative(Nel.semigroup<Error>())
        .map(
            validateGreaterThan3("H"),
            validateIsNumber("a")
        ) { (name, age) ->
            Person (name, age)
        }.fix()


    // FailFastStrategy
    val e: Either<String, Int> = Either.right(1)












    result1.fold(
        { errors -> errors.toList().forEach { println("error $it") } },
        { person -> println("Person valid: ${person}") }
    )
//
//    println("--------")
//
//    val result2 = ValidatedNel
//        .applicative(Nel.semigroup<Error>())
//        .map(
//            validateGreaterThan3("Ju"),
//            validateIsNumber("14")
//        ) { (name, age) -> Person (name, age) }.fix()
//
//    result2.fold(
//        { errors -> errors.toList().forEach { println("error $it") } },
//        { person -> println("Person valid: ${person}") }
//    )
//
//    println("--------")
//
//    val result3 = ValidatedNel
//        .applicative(Nel.semigroup<Error>())
//        .map(
//            validateGreaterThan3("Ju"),
//            validateIsNumber("AA")
//        ) { (name, age) -> Person (name, age) }.fix()
//
//    result3.fold(
//        { errors -> errors.toList().forEach { println("error $it") } },
//        { person -> println("Person valid: ${person}") }
//    )

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


fun singleValidateIsNumber(value: String): Validated<Error, Int> =
        when (isNumber(value)) {
            true -> value.toInt().valid()
            false -> Error("I'm not a number").invalid()
        }

fun singleValidateGreaterThan3 (value: String): Validated<Error, String> =
        when (isGreaterThan3(value)) {
            true -> value.valid()
            else -> Error("I'm not greater than 3").invalid()
        }



fun isGreaterThan3(value: String): Boolean = value.length > 3

fun isNumber(value: String): Boolean = value.toIntOrNull() != null


fun validateCountry(oCountry: Option<String>): ValidatedNel<Error, String> =
        when (oCountry) {
            is Some -> oCountry.t.valid()
            is None -> Error("nao tenho valor").invalid()
        }.toValidatedNel()
