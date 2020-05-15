package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertEquals

fun sum(x: Int, y: Int): Int = x + y

fun sub(x: Int, y: Int) = x - y



class Topic3 {

    @Test
    fun foobar() {

        assertEquals(5, sum(3, 2))
        assertEquals(1, sub(3, 2))

    }

}