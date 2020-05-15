package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// internal = any client inside this module
// protected = private + visible in subclasses too
// sealed = in the same file
open class ServiceOne {
    open fun foobar() {}
}


class Topic15One : ServiceOne() {
    override fun foobar() {
        super.foobar()
        println("override")
    }
}

// ALGE
sealed class Service2(a: Int)


class Topic15Example1 constructor(a: Int) {

    constructor(a: Int, b: Int) : this(a * b)

    val another: Int

    init {
        this.another = a
        // do something else
    }

}


class Topic15Example2 constructor(a: Int) {

    var a: Int = 0
        get() {
            return field * 2
        }
        internal set

    constructor(a: Int, b: Int) : this(a * b)

    val another: Int

    init {
        this.another = a
        // do something else
    }

}

data class Connection(val url: String)


// Lazy
class Topic15Example3 constructor(private val a: Int) {
    val b: Int by lazy {
        println("b lazy init")
        this.a * 2
    }
}


// Delegation
interface MyRepository {
    fun saveFromMyRepository(): Boolean
}

class MyRepositoryImpl: MyRepository {
    override fun saveFromMyRepository() = true
}


class MyService(val myRepository: MyRepository): MyRepository by myRepository {
    fun doSomething() {
        saveFromMyRepository()
    }
}

class Topic15Example4 {

    @Test
    fun `should invoke lazy one time`() {
        val t = Topic15Example3(2)
        assertEquals(4, t.b)
        assertEquals(4, t.b)
        assertEquals(4, t.b)
    }


    @Test
    fun `should use delegate `() {
        assertTrue {
            MyService(MyRepositoryImpl())
                    .saveFromMyRepository()
        }
    }

}


class Topic15Example5 {
    companion object CustomName {
        const val page: Int = 0

        @JvmField
        val user: User? = null


        @JvmStatic
        fun doSomething() {

        }
    }

    @Test
    fun test() {
        doSomething()
        page
        // java
        doSomething()
    }
}


object Topic15Example6 {
    const val PI: Double = 3.14
    fun foobar() {}
}




