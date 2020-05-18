package br.com.kotlin.dsl



fun String.reversedAndUppercase(): String {
    return this.reversed().toUpperCase()
}

fun main() {
    println("abcd".reversedAndUppercase())


    // jar/library is not yours
    // you don't want to pollute the data class
    // increase your code readability

}


// increase your code readability
//model
data class Customer(val id: Int, val firstname: String, val lastname: String)

// representation
data class CustomerRepresentation(val fullname: String) {

    companion object CustomerRepresentationUtil {

        fun toRepresentation(c: Customer): CustomerRepresentation {
            return CustomerRepresentation("${c.lastname}, ${c.firstname}")
        }

    }

}

fun conver() {

    val customers = listOf(
            Customer(1, "foo1", "bar1"),
             Customer(1, "foo2", "bar2"))

    // without ext
    val cRepresentations =
            customers.map {
                CustomerRepresentation.CustomerRepresentationUtil.toRepresentation(it)
            }


    // with ext
    fun List<Customer>.toCustomerRepresentation(): List<CustomerRepresentation> {
        TODO()
    }


    val r2 = customers.toCustomerRepresentation()



    // doest not compile
    // listOf(1,2,3).toCustomerRepresentation()



}
