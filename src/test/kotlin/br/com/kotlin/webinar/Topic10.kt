package br.com.kotlin.webinar

import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertTrue


fun Int.toBDWithScale4() =
        BigDecimal.valueOf(this.toLong(), 4)



data class Customer(val id: CustomerId, val name: String)

data class CustomerRepresentation(val name: String)


fun List<Customer>.toRepresentation(): List<CustomerRepresentation> {
    return this.map { CustomerRepresentation(it.name) }
}





fun String.multipleBy(x: Int): Int {
    return this.toInt() * x
}



typealias CustomerId = Int

class Topic10 {


    @Test
    fun test() {


        1.toBDWithScale4()




        assertEquals(BigDecimal.valueOf(1L, 4),
                     1.toBDWithScale4())
















        // typealias CustomerId = Int


    val customers = listOf(Customer(1, "Mike"),
                           Customer(2, "Helena"),
                           Customer(3, "Julia"))

    val customerRepresentations = customers.map { customer -> customer.name }



    class CustomerUtil {
        fun toCustomerRepresentation(customers: List<Customer>): List<String> {
            return customers.map { customer -> customer.name }
        }
    }


        customers.toRepresentation()



    // alternative ext

























        assertTrue("aaaa".myEquals("aAAA"))



        assertTrue("aaaa" myEquals "aAAA")




        val maps = mapOf(1 to "value1", 2 to "value1")



        assertEquals(Pair("aFirst", "bSecond"), "aFirst" to "bSecond")
    }

}


infix fun String.myEquals(other: String): Boolean =
        this.toUpperCase() == other.toUpperCase()
