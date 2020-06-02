

data class Money(val value: Double, val currency: String) {
    operator fun plus(m2: Money): Money {
        TODO()
    }
}

fun over() {

    val m1 = Money(1.0, "EUR")
    val m2 = Money(2.0, "EUR")

    val t1 = Money( m1.value + m2.value, "EUR"  )


    // plus (verbose way)

    val t2 = m1 + m2


































    /*
        a + b	a.plus(b)
        a - b	a.minus(b)
        a * b	a.times(b)
        a / b	a.div(b)
        a++	a.inc() + see below
        a--	a.dec() + see below
        +a	a.unaryPlus()
        -a	a.unaryMinus()
        !a	a.not()
        a in b	b.contains(a)
        a !in b	!b.contains(a)
        a % b	a.rem(b), a.mod(b) (deprecated)
        a..b	a.rangeTo(b)
        a[i]	a.get(i)
        a[i, j]	a.get(i, j)
        a[i] = b	a.set(i, b)
        a[i, j] = b	a.set(i, j, b)
     */

    (1..10).forEach { println(it) }












    class Power(var status: String = "off") {

        infix fun level(value:Int) {

        }

        operator fun invoke(s: String) {
            TODO("Not yet implemented")
        }

        operator fun invoke(λ: () -> String) {
            TODO("Not yet implemented")
        }
    }

    val power = Power()

    power level 100


    power.status = "on"
    power.status = "off"

    power("on")
    power("off")

    power {
        "on"
    }




}