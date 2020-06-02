package br.com.kotlin.dsl


// fun ??

fun hello() {
    println("hello")

    val x = " asfsdf"
    var y = 1
    y = 2
}















fun sum1(x: Int, y: Int): Int = x + y
































// data class (show byte code)
data class Point(val x: Int, val y: Int)

























fun plot() {
    val point = Point(1, 2)

    val (x, y) = point

    val p2 = point.copy(x = 2)

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
        Point(1, 2) -> value
        true -> " true"
        else -> "boom"
    }
}
