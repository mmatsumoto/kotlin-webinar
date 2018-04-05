package br.com.zup.kotlin

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class CoroutinesTest {

    @Test
    fun `threads are too heavy weight`() {

        assertFailsWith<OutOfMemoryError> {

            val jobs = List(100_000) {
                thread {
                    println("launch $it, waiting 1000L")
                    Thread.sleep(1000L)
                    print("$it done ")
                }
            }
            jobs.forEach { it.join() }
        }

    }

    private val service = CoroutinesService()

    @Test
    fun `kotlin coroutines are light weight`() {

        runBlocking {

            val jobs = List(1_000_000) {
                launch {
                    println("launch $it, waiting 1000L")
                    delay(300L)
                    print("$it done")
                }
            }
            jobs.forEach { it.join() }
        }
    }

    @Test
    fun `no callback hell sequencial`() {

        launch {

            val catalogOne = service.asyncFindCatalogId(1)

            val offers = service.asyncFindOffersByCatalogId(catalogOne.toInt())

            assertEquals(listOf("Offer1", "Offer2"), offers)

        }

    }


    @Test
    fun `test async call - parallel`() {

        val findOne1 = async { service.findCatalogId(1) }
        val findOne2 = async { service.findCatalogId(2) }

        runBlocking {
            println("running blocking code")

            val catalog1 = findOne1.await()
            val catalog2 = findOne2.await()

            print("Combined: ($catalog1) ($catalog2)")
        }
    }

    @Test
    fun `with threads`() {
        println("Starting jobs")

        val jobs = List(5) {
            launch {
                service.findCatalogId((1..100).random())
            }
        }

        println("Jobs launched")

        runBlocking {
            println("starting the runBlocking: waiting for the join")
            jobs.forEach { it.join() }
            println("ending the runBlocking: join done")
        }
    }

    @Test
    fun `test await`() {
        runBlocking {
            println("lancando jobs")
            val j1 = service.testAwait("1")
            val j2 = service.testAwait("2")
            val j3 = service.testAwait("3")
            println("jobs lancado")


            println("esperando")
            j1.await()
            j2.await()
            j3.await()
            println("terminei de esperar")
        }


    }
}


class CoroutinesService {

    fun testAwait(id: String) = async {
        println(" - iniciando test await $id")
        delay(5, TimeUnit.SECONDS)
        println(" - terminando test await $id")
    }

    suspend fun findCatalogId(id: Int, timeout: Int = (5..10).random()): String {
        println("starting findCatalogId($id) timeout: $timeout")
        delay(timeout.toLong(), TimeUnit.SECONDS)
        println("returning from findCatalogId($id)")
        return id.toString()
    }

    suspend fun findOffers(id: Int, timeout: Int = (5..10).random()): List<String> {
        println("starting findOffers($id) timeout: $timeout")
        delay(timeout.toLong(), TimeUnit.SECONDS)
        println("returning from findOffers($id)")
        return listOf("Offer1", "Offer2")
    }

    suspend fun asyncFindCatalogId(id: Int, timeout: Int = (5..10).random()): String = async {
        findCatalogId(id, timeout)
    }.await()

    suspend fun asyncFindOffersByCatalogId(id: Int, timeout: Int = (5..10).random()): List<String> = async {
        findOffers(id, timeout)
    }.await()

}


fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) + start