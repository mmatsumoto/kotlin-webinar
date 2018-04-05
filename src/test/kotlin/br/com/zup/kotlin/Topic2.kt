package br.com.zup.kotlin

class Topic2 {

    fun topic2(): String {

        val s: String = "str"

        var i = 1

        val s1 = "str2"

        val s2: String? = null

        s2?.toUpperCase()

//        s2!!.toUpperCase()


        return s2?.toUpperCase() ?: s1
    }

}