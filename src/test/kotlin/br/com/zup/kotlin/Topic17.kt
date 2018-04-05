package br.com.zup.kotlin

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith



fun headFactorial(n: Long): Long =
        if (n <= 1)
            1
        else
            n * headFactorial(n - 1)


class Topic17 {

    @Test
    fun `should test head factorial and throws StackOverflow`() {

        assertEquals(1, headFactorial(0))
        assertEquals(1, headFactorial(1))
        assertEquals(2, headFactorial(2))
        assertEquals(6, headFactorial(3))
        assertEquals(24, headFactorial(4))
        assertEquals(120, headFactorial(5))

        assertFailsWith<StackOverflowError> {
            headFactorial(100000)
        }
    }


    @Test
    fun `should test tail factorial without throwing StackOverflow`() {
        tailFactorial(BigInteger.valueOf(10_000))
    }

}


fun tailFactorial(n: BigInteger): BigInteger =
        tail(BigInteger.ONE, n)

tailrec fun tail(accumulator: BigInteger, n: BigInteger): BigInteger =
        if (n <= BigInteger.ONE) {
            accumulator
        }
        else {
            tail(accumulator * n, n.dec())
            // using operator *
        }
