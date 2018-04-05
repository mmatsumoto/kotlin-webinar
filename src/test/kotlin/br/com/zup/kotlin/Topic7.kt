package br.com.zup.kotlin

import org.junit.Test
import java.time.LocalDateTime


fun foobar1(a: Int,
            date: LocalDateTime = LocalDateTime.now(),
            s: String? = null) {
    println("$a, $date, $s")


}


fun foobar2(date: LocalDateTime = LocalDateTime.now(),
            a: Int,
            s: String = "") {
    println("$a, $date, $s")
}

class Topic7 {

    @Test
    fun teste1() {

        foobar1(1)
        foobar1(1, LocalDateTime.now().plusDays(1))
        foobar1(1, LocalDateTime.now().plusDays(1), "myString")

        foobar1(s = "myString",
                a = 1,
                date = LocalDateTime.now().plusDays(1))

        foobar2(a = 2)

    }


}