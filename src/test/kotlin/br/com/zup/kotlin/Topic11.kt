package br.com.zup.kotlin

import org.junit.Test
import kotlin.test.assertEquals



/*
+a	a.unaryPlus()
-a	a.unaryMinus()
!a	a.not()
a++	a.inc() + see below
a--	a.dec() + see below
a + b	a.plus(b)
a - b	a.minus(b)
a * b	a.times(b)
a / b	a.div(b)
a % b	a.rem(b), a.mod(b) (deprecated)
a..b	a.rangeTo(b)

 */
class Topic11 {

    @Test
    fun `test operators `() {
        var foobar1 = FoobarOperator()
        var foobar2 = FoobarOperator()

        foobar1++
        foobar1++
        assertEquals(3, foobar1.count)

        foobar2++
        foobar2++
        foobar2++
        assertEquals(4, foobar2.count)

        foobar1 + foobar2
        assertEquals(7, foobar1.count)
    }
}
















class FoobarOperator {

    var count: Int = 1
        private set

    operator fun plus(other: FoobarOperator): FoobarOperator {
        this.count += other.count
        return this
    }

    operator fun inc(): FoobarOperator {
        this.count++
        return this
    }
}