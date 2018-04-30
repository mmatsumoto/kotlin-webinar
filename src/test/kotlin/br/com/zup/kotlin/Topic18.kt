package br.com.zup.kotlin

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith




class CoroutinesTest {
    private val service = CoroutinesService()

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


    @Test
    fun `kotlin coroutines are light weight`() = runBlocking {

        val jobs = List(1_000_000) {
            launch {
                println("launch $it, waiting 200L")
                delay(200L)
                println("$it done ")
            }
        }
        jobs.forEach { it.join() }
    }


    @Test
    fun `no callback hell, sequential call`() = runBlocking {
        val catalog = service.findCatalogId(CatalogId(1))

        val offers = service.findOffers(catalog.id)

        assertEquals(listOf("Offer1", "Offer2"), offers.map { it.name })
    }


    @Test
    fun `test async call`() {

        val findOne1 = async { service.findCatalogId(CatalogId(1)) }
        val findOne2 = async { service.findCatalogId(CatalogId(2)) }

        runBlocking {
            println("running blocking code")

            val catalog1 = findOne1.await()
            val catalog2 = findOne2.await()

            print("($catalog1) ($catalog2)")
        }
    }

    @Test
    fun `with threads`() {
        println("Starting jobs")

        val jobs = List(5) {
            launch {
                service.findCatalogId(CatalogId((1..100).random()))
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
        measureTimeMillis {
            println(" - starting test await $id")
            delay(5, TimeUnit.SECONDS)
            println(" - test await $id done")
        }.also { println("Time await $id: $it") }
    }


    suspend fun findCatalogId(id: CatalogId): Catalog {

        val timeout = (5..10).random()
        println("starting findCatalogId($id) timeout: $timeout")
        delay(timeout.toLong(), TimeUnit.SECONDS)
        println("returning from findCatalogId($id)")
        return Catalog(id, "catalog $id")
    }
    suspend fun findOffers(id: CatalogId): List<Offer> {


        val timeout = (5..10).random()
        println("starting findOffers($id) timeout: $timeout")
        delay(timeout.toLong(), TimeUnit.SECONDS)
        println("returning from findOffers($id)")
        return listOf(
                Offer(id, OfferId(1), "Offer1"),
                Offer(id, OfferId(2), "Offer2"))
    }



}


fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) + start



typealias CatalogId = Integer
typealias OfferId = Integer
typealias CatalogName = String
typealias OfferName = String

data class Catalog(val id: CatalogId, val name: CatalogName)
data class Offer(val catalogId: CatalogId, val id:OfferId, val name: OfferName)