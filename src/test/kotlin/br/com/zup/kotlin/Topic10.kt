package br.com.zup.kotlin

import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertTrue


fun Int.bdScale4() =
        BigDecimal.valueOf(this.toLong(), 4)


class Topic10 {


    @Test
    fun test() {


        assertEquals(BigDecimal.valueOf(1L, 4),
                     1.bdScale4())






































        assertTrue("aaaa".myEquals("aAAA"))



        assertTrue("aaaa" myEquals "aAAA")







        assertEquals(Pair("aFirst", "bSecond"), "aFirst" to "bSecond")
    }

}


infix fun String.myEquals(other: String): Boolean =
        this.toUpperCase() == other.toUpperCase()
