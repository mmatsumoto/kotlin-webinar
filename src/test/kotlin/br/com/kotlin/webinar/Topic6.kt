package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertEquals

class Topic6 {

    @Test
    fun `destructuring object 1`() {

        val user = User("user1",
                                                                 City("cityName1"))

        val (userName, city) = user


        val myPair = Pair(1,2)
        myPair.first
        myPair.second

        val (someGoodName, anotherGoodName) = myPair


        assertEquals("user1", userName)
        assertEquals(City("cityName1"), city)
    }


    @Test
    fun `destructuring object 2`() {

        val (userName, _) = User("user1",
                                                                          City(
                                                                                  "cityName1"))
        assertEquals("user1", userName)

    }

    @Test
    fun `destructuring object 3`() {

        val users = listOf(
                User("user1",
                                                              City("cityName1")),
                User("user2",
                                                              City("cityName2")))

        for ((name, _) in users) {
            println("name ${name.toUpperCase()}")
        }
    }

}