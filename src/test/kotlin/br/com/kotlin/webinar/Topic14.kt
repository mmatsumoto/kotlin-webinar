package br.com.kotlin.webinar

import org.junit.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue


class Topic14 {

    @Test
    fun `I have a cool name`() {

        assertTrue {

            true

        }

        assertFails {

        }

    }

    @Test
    fun `this test is expecting a  exception`() {
        assertFailsWith <ArithmeticException> {

            2 / 0

        }
        assertFailsWith (ArithmeticException::class) {
            2 / 0
        }
    }

    @Test
    fun `testing function TODO`() {
        assertFailsWith<NotImplementedError> {
            doSomething1(1)
        }
    }


    @Test
    fun `how long?`() {
        val time = measureTimeMillis {
            delay()
        }
        assertTrue { time >= 2000L }
    }
}


fun doSomething1(x: Int): Int {
    TODO("dont call this method yet")
}


@Deprecated("dont use anymore",
            level = DeprecationLevel.WARNING,
            replaceWith = ReplaceWith("good()"))
fun notGood() {

}
fun good() {}

fun testingDeprecated() {
    notGood()
}


// Erasing Erasure
@JvmName("sumInt")
fun sum(l: List<Int>) {

}

@JvmName("sumLong")
fun sum(l: List<Long>) {

}

// no exception
fun delay(millis: Long = 2000L) {
    Thread.sleep(millis)
}






