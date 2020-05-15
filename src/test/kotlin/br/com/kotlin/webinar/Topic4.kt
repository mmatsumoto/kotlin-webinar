package br.com.kotlin.webinar

import org.junit.Test

class Topic4 {

    fun intRange() {

        val numbers = (1..10)

        for (i in numbers) {
            print("i=$i")
        }





        for (i in 1..100) {
            print("i=$i")
        }

    }

    @Test
    fun `testing string template`() {
        val s1 = "foobar"

        println ("s1 = $s1")
        println ("s1 = ${s1.toUpperCase()}")

        val s2: String = """
                |asdf 
                |asdf
                |asdf
        """.trimMargin()

        println (s2)
    }

}