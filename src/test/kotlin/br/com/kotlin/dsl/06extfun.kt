package br.com.kotlin.dsl



fun String.reversedAndUppercase(): String {
    return this.reversed().toUpperCase()
}

fun main() {
    println("abcd".reversedAndUppercase())









}


// increase your code readability
//model
data class Customer(val id: Int, val firstname: String, val lastname: String)

// representation

data class CustomerRepresentation(val fullname: String) {
    companion object {
        fun toRepresentation(c: Customer): CustomerRepresentation {
            return CustomerRepresentation("${c.lastname}, ${c.firstname}")
        }
    }
}

fun conver() {

    val customers = listOf(
            Customer(1, "foo1", "bar1"),
             Customer(1, "foo2", "bar2"))


    val cRepresentations =
            customers.map {
                CustomerRepresentation.Companion.toRepresentation(it)
            }


    fun List<Customer>.toRepresentation(): List<CustomerRepresentation> {
        TODO()
    }


    val r2 = customers.toRepresentation()



//    listOf(1,2,3).toRepresentation()



}
