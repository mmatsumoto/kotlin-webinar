package br.com.kotlin.webinar

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

class CoroutinesTest {
    private val service = CoroutinesService()
//
    @Test
    fun `threads are too heavy weight`() {

//        assertFailsWith<OutOfMemoryError> {
            val jobs = List(10_000) {
                thread {
                    println("launch $it, waiting 1000L")
                    Thread.sleep(2000L)
                    print("$it done ")
                }
            }
            jobs.forEach { it.join() }
//        }
    }

    @Test
    fun `kotlin coroutines are light weight`()  {

        val jobs = List(1_000_000) {
            GlobalScope.launch {
                delay(2000L)
                println("$it done ")
            }
        }

        runBlocking {
            jobs.forEach { it.join() }
        }

    }

     @Test
    fun `test async call`()  {
        val findOne1 = GlobalScope.async { service.findCatalogId(1) }
        val findOne2 = GlobalScope.async { service.findCatalogId(2) }

         println("Awaaaaaaaiting")
         runBlocking {
             val catalog1 = findOne1.await()
             val catalog2 = findOne2.await()
             print("($catalog1) ($catalog2)")
         }
    }
}

class CoroutinesService {

    fun findCatalogId(id: Int): Catalog {
        val timeout = (5..12).random()
        println("starting findCatalogId($id) timeout: $timeout (s)")
        delay(timeout.toLong() * 1000)
        println("returning from findCatalogId($id)")
        return Catalog(id, "catalog $id")
    }

}

data class Catalog(val id: Int, val name: String)