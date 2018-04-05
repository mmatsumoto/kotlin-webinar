package br.com.zup.kotlin

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


fun <T> toJson(clazz: Class<T>): T = clazz as T

// does not compile
//fun <T> something(): T {
////    T::class.java // you can't access the type T because it's only available at compile time
//    return toJson(T::class.java)
//}

inline fun <reified T> something1(clazz: Class<T>): T {
    return toJson(T::class.java)
}