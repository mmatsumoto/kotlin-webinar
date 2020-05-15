package br.com.kotlin.webinar

class Topic8 {

    fun foobar1(a: Any) {

        if (a is String) {

            // ((String)a).toUpperCase()
            a.toUpperCase()
        }

        if (a is Int) {
            a.toLong()
        }
    }

    fun foobar2(a: Any) {
        val b = a as String
        b.toUpperCase()
    }

}




