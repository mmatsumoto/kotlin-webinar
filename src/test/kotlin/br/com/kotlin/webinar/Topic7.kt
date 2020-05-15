package br.com.kotlin.webinar

import org.junit.Test
import java.time.LocalDateTime





class JavaClass {
    fun foobar(i: Int, date: LocalDateTime, s: String?) {
        TODO()
    }

    fun foobar(i: Int, date: LocalDateTime) {
        foobar(i, date, "somedefaultvalue")
    }

    fun foobar(i: Int) {
        foobar(i, LocalDateTime.now())
    }
}




















fun foobar1(i: Int,
            date: LocalDateTime = LocalDateTime.now(),
            s: String? = null) {

    println("$i, $date, $s")
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
                                                         i = 1,
                                                         date = LocalDateTime.now().plusDays(1))

        foobar2(a = 2)

    }


}