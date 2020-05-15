package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


data class City(val name: String)



data class User(val name: String, val city: City)





class Topic5 {

    @Test
    fun test1() {

        val city1 = City("name1")
        city1.hashCode()
        city1.toString()
        city1.equals(null)
        city1.name













        val cityV2 = city1.copy(name ="name 1.1")



        val city2 = City("name2")
        val city3 = City("name1")

        assertTrue(city1 == city3)
        assertFalse(city1 == city2)


        assertFalse(city1 === city3)
        assertTrue(city1 === city1)

    }



}