package br.com.kotlin.dsl


// fun hello

fun hello() {
    println("hello")

    val x: Int = 1
    val y = 1
}

// sum1 without type inference
fun sum1(x: Int, y: Int): Int {
    return x + y
}

// sum2 one line fun
fun sum2(x: Int, y: Int): Int = x + y

// sum3 oneline and inference
fun sum3(x: Int, y: Int) = x + y


// data class (show byte class)
data class Point(val x: Int, val y: Int)

fun plot() {
    val p1 = Point(1,2)

    val (x, y) = p1

    val p2 = p1.copy(x = 2)

    println("p2 = ${p2.x}")
}

fun returningExpressions() {
    val x = 1

    val x1 = if(x == 1) {
        x * 10
    } else {
        x * x
    }

    val x2 = try {
        10 / 0
    } catch (e: ArithmeticException) {
        -1
    }

    val value: Any = 1

    val x3 = when(value) {
        is String -> value.toUpperCase()
        is Int -> value * 2
        else -> "boom"
    }
}
