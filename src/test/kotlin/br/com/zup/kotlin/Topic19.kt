package br.com.zup.kotlin

import java.lang.RuntimeException

sealed class Either<L, R> {
    class Left<L, R>(internal val l: L) : Either<L, R>() {
        override fun toString(): String = "Left $l"
        fun failThrow(): Nothing = if (l is Exception) throw l else throw IllegalAccessException(toString())
    }

    class Right<L, R>(internal val r: R) : Either<L, R>() {
        override fun toString(): String = "Right $r"
    }

    fun <T> foldCompose(left: (L) -> Either<L, T>, right: (R) -> Either<L, T>): Either<L, T> {
        return when (this) {
            is Either.Left<L, R> -> left(this.l)
            is Either.Right<L, R> -> right(this.r)
        }
    }

    inline fun fold(left: (L) -> Unit, right: (R) -> Unit) {
        when (this) {
            is Either.Left<L, R> -> left(this.fail())
            is Either.Right<L, R> -> right(this.get())
        }
    }

    fun isRight(): Boolean = !isLeft()

    inline fun success(right: (R) -> Unit) = right(get())

    fun get(): R {
        return when (this) {
            is Either.Left<L, R> -> this.failThrow()
            is Either.Right<L, R> -> this.r
        }
    }


    fun isLeft(): Boolean = when (this) {
        is Either.Left<L, R> -> true
        else -> false
    }

    inline fun fail(left: (L) -> Unit) = left(fail())

    fun fail(): L {
        return when (this) {
            is Either.Left<L, R> -> this.l
            else -> throw IllegalAccessException(toString())
        }
    }

    fun getOrElse(supplier: (L) -> Either<L, R>): Either<L, R> {
        return when (this) {
            is Either.Left<L, R> -> supplier(this.fail())
            is Either.Right<L, R> -> this
        }
    }

    companion object {
        fun <L, R> right(r: R) = Either.Right<L, R>(r)

        fun <L, R> left(l: L) = Either.Left<L, R>(l)
    }
}

fun trySomething(x: Int, success: Boolean = false): Either<Exception, Int> {
    println("calling func $x")
    return if (success) {
        Either.right(x)
    } else {
        Either.left(RuntimeException("lancando exception $x"))
    }
}



fun main(args: Array<String>) {

    println("--------right")
    val r1 = Either.right<Exception, Int>(1)

    if (r1.isRight()) {

        r1.success {
            println("deu certo $it")
        }



        println("deu certo value ${r1.get()}")
    }







    r1.fold({ println(" deu  errado $it") }, { println("deu sucesso  $it") })






    r1
            .foldCompose({
                       println("errado: $it")
                       Either.right<Exception, Int>(1)
                   },
                   {
                       println("certo: $it")
                       Either.right(it)
                   })
            .foldCompose({
                             println("another foldcompose errado: $it")
                             Either.left<Exception, Int>(it)
                         },
                         {
                             println("another foldcompose certo: $it")
                             Either.right<Exception, Int>(it)
                         })
            //.get()
            //.fail()
            .fold({ println("foldCompose errado $it") }, { println("foldCompose sucesso  $it") })


















//    println("\n\n--------left")
//    val l2 = Either.left<Exception, Int>(RuntimeException("deu ruim"))
//
//
//
//    l2.fail {
//        println("deu ruim $it")
//    }
//
//
//    println("deu ruim value ${l2.get()}")
//
//
//
//
//    l2.foldCompose({
//                       println("errado: $it")
//                       Either.left<Exception, Int>(it)
//                   },
//                   {
//                       println("certo: $it")
//                       Either.right<Exception, Int>(it)
//                   })
//            .fold({ println("foldCompose errado $it") }, { println("foldCompose sucesso $it") })
//
//
//





    println("\n\n-------- chain call when left getOrElse()")
    val x = trySomething(1)
            .getOrElse { trySomething(2, true) }
            .getOrElse { trySomething(3) }
            .getOrElse { trySomething(4, true) }
            .get()

    println(x)

}