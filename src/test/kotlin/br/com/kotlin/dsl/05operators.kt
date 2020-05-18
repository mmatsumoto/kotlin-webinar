

data class Money(val value: Double, val currency: String) {

    operator fun plus(other: Money): Money {
        return Money( this.value + other.value, this.currency  )
    }
}

fun over() {

    val m1 = Money(1.0, "EUR")
    val m2 = Money(2.0, "EUR")

    val t1 = Money( m1.value + m2.value, "EUR"  )


    // plus (verbose way)
    val t2 = m1.plus(m2)

    val t3 = m1 + m2


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



    class Power(var status: String) {

        infix fun level(intensity: Int) {

        }

        operator fun invoke(s: String) {
            this.status = s
        }

        operator fun invoke(lambda: () -> String) {
            this.status = lambda()
        }
    }


    val power = Power("off")

    power.status = "on"
    power.status = "off"

    // invoke - without sugar
    power.invoke("on")
    power.invoke("off")

    // with sugar
    power ("on")
    power ("off")


    // lambda without sugar
    power({ "on" })

    // lambda
    power {
        // i can do more stuffs here
        "on"
    }



    // infix

    power.level(1)

    power level 1
}