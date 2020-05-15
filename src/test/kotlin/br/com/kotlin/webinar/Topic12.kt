package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertEquals



val myFunc: (x: Int, y: Int) -> Int = { x, y -> x * y }

// myFunc2(1,2)


fun fnReturningLambda(x: Int): (x:Int) -> Int {
    return { n -> n * x }
}







class Topic12 {


    //fun hightOrderFunction(age: Int, myFunc: (Int, String) -> Int): Int
    fun hightOrderFunction(age: Int = 0,
                           myFunc: (age: Int) -> Int): Int {

        return myFunc(age)
    }




    fun test1() {

        hightOrderFunction(10,
                           { age -> age * 2 })
    }



   // it
    fun test2() {
        hightOrderFunction(10, { it * 2 })
    }




    fun test3() {
        hightOrderFunction(10) {
            it * 2
        }
    }


    fun hightOrderFunction2(age: Int = 0,
                            name: String = "default",
                            myFunc: (age: Int, name: String) -> Int): Int {
        return myFunc(age, name)
    }

    fun test4() {
        hightOrderFunction { it * 2 }
    }

    fun test5() {
//        hightOrderFunction2 {
//
//        }


        hightOrderFunction2 { age, _ -> age * 2 }
    }

    @Test
    fun testingReturningLambda() {
        val x1 = fnReturningLambda(2).invoke(2)

        val x2 = fnReturningLambda(2)(2)
        assertEquals(4, x1)
    }


}


val isOdd: (x:Int) -> Boolean = { it % 2 != 0 }

fun print(x:Int) { println("number: $x") }

fun functioReferences(args: Array<String>) {

    // model 1
    listOf(1, 2, 3, 4)
            .filter { isOdd(it) }
            .forEach { print(it) }






    // model 2
    listOf(1, 2, 3, 4)
            .filter(isOdd)
            .forEach(::print)

}











inline fun something(x: Int): Int {
    val t = x * x

    println(t)

    // more code

    return t
}


fun client() {
    something(
            something(
                    something(2)))
}

/*
// stack looks like:
| something(2)                        |
| something(something(2))             |
| something(something(something(2)))  |
| client()                            |
*/




























// if it's inline
fun client2() {
    // copy / past 1

    val r1 = {
        val t1 = 2 * 2
        println(t1)
        // more code
        t1
    }()

    // copy / past 2
    val r2 = {
        val t2 = r1 * r1
        println(t2)
        // more code
        t2
    }()


    // copy / past 2
    val r3 = {
        val t3 = 5 * 5
        println(t3)
        // more code
        t3
    }()

}
/*
// stack looks like:
| client() |
*/


// one line func
// lambdas


fun async(pool: Int = 1, apply: () -> Int): Int {
    TODO()
}

fun clientAsync() = run {
    // dod

    1
}







fun listFilter() {
    val myList =  listOf(1,2,3,4,5,6,7,8,9,10);

    myList.forEach {
        println("element $it")
        if (it % 2 == 0)
            return
    }
}

fun main(args: Array<String>) {
    listFilter()
}





// create a better example here, showing how the copy/past will exactly works.


// inline
fun testMyForeach(): Unit {

    println(myForeach())

//        assertEquals(-1, myForeach())
//        assertEquals(2, myForeach())
}

fun myForeach(): Int {
    (1..100).myForeach {
        if (it % 2 == 0)
            return it // 2
    }
    return -1
}


inline fun <T> Iterable<T>.myForeach(f: (T) -> Unit) {
    for (e in this) f(e)
}





// Show real example acceptance tests here



fun <T> toJson(clazz: Class<T>): T = clazz as T

// does not compile
//fun <T> something(): T {
////    T::class.java // you can't access the type T because it's only available at compile time
//    return toJson(T::class.java)
//}

inline fun <reified T> something1(clazz: Class<T>): T {
    return toJson(T::class.java)
}