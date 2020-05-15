package br.com.kotlin.webinar

import org.junit.Test
import kotlin.test.assertEquals

class Topic13 {

    val uberlandia = City("Uberlândia")
    val araguari = City("Araguari")

    val luke = User("Luke", uberlandia)
    val leia = User("Leia", uberlandia)
    val vader = User("Vader", araguari)
    val malgus = User("Malgus", araguari)

    val users = listOf(luke, leia, vader, malgus)


    @Test
    fun test1() {

        val a = listOf(1, 2, 3)

        val b = listOf(4, 5)


        emptyList<Int>()

        val c = a + b
        assertEquals(listOf(1, 2, 3, 4, 5), c)



        val d = a.toMutableList()
        d.add(1111)


        setOf<Int>()
        emptySet<Int>()

        mapOf<Int, String>()
        emptyMap<Int, String>()


        mapOf(1 to "aaa",
              2 to "bbb",
              3 to "ccc")
    }


    @Test
    fun `no collector filter, map, reduce, fold, foldRight, any, none, all, first, find`() {

        val numbers = (1..100)

        // no stream,  no collector, lambda last parameter
        val l = numbers.filter {

            it % 2 == 0


        }

        /**
          List<Int> l = l.stream()
              .filter(e -> e % 2 == 0)
               .collect(Collectors.toList())
         */

        numbers.filter { it % 2 == 0 }
                .map { it * 2 }

        numbers.filterIndexed { index, i ->
                    print("index: $index")
                    i % 2 == 0
                }
                .mapIndexed { index, i ->
                    print("map: $index")
                    i * 2
                }


        numbers.first { it % 2 ==0 }


        numbers.find { it % 2 ==0 } ?: println("null")


        numbers.none { it == 30 }
        numbers.any { it == 30 }


        numbers.sum()
        numbers.sumBy { it }

        numbers.min()
        numbers.minBy { it }

        numbers.max()
        numbers.maxBy { it }

        numbers.reduce { acc, i -> acc + i }

        numbers.fold(0, {acc, i -> acc + i})

        numbers.toList().foldRight(0, {acc, i -> acc + i})

        numbers.take(1)
        numbers.drop(10)
        numbers.toList().slice(1..10)

    }

    @Test
    fun test3() {
        assertEquals(
                listOf("Leia", "Luke", "Malgus", "Vader"),
                users.map { it.name }
                        .sorted()
//                        .sortedBy { it }
                        .distinct())
    }


    @Test
    fun test4() {

        val (even, odd) = (1..10)
                .toList()
                .partition { it % 2 == 0 }

        assertEquals(listOf(2, 4, 6, 8, 10), even)
        assertEquals(listOf(1, 3, 5, 7, 9), odd)

    }

    @Test
    fun test5() {

        assertEquals(listOf(
                1 to 4,
                2 to 5,
                3 to 6),

                     listOf(1, 2, 3).zip(listOf(4, 5, 6)))

    }


    @Test
    fun `should map by city`() {

        // list of Pairs
        val groupedByCity = mapOf(
                uberlandia to listOf(luke, leia),
                araguari to listOf(vader, malgus))

        assertEquals(groupedByCity, users.groupBy { it.city })


        val listOfPair = listOf(
                "Berlin" to "Mike",
                "Berlin" to "Kathleen",
                "NY" to "Julia",
                "NY" to "Helena")

        // list elements
        val pairGroupedBy = listOfPair.groupBy({ it.first }, { it.second })


        /**
         Map<String, List<String>> pairGroupedBY = listOfPair.stream()
              .collect(Collectors.groupingBy(
                   Pair::getFirst, Collectors.mapping(Pair::getSecond, Collectors.toList())
                ))
         */

//        assertEquals(mapOf(
//                "Berlin" to listOf("Mike", "Kathleen"),
//                "NY" to listOf("Julia", "Helena")),
//                     pairGroupedBy)
        println("pairGrouped: $pairGroupedBy")

    }

    @Test
    fun `to list of pairs`() {

        // list of Pairs
        val groupedByCity = mapOf(
                uberlandia to listOf(luke, leia),
                araguari to listOf(vader, malgus))

        assertEquals(listOf(uberlandia to listOf(luke, leia), araguari to listOf(vader, malgus)),
                     groupedByCity.toList())

    }

    @Test
    fun `asSequence`() {

        (1..10).map {
            println("double it $it")
            it * 2
        }
//                .forEach {
//            println("result: $it")
//        }


        generateSequence(1) {
            println("sequence: double it $it")
            it * 2
        }.takeWhile { it < 3 }
                .forEach {
                    println("it $it")
                }


        listOf(1,2,3,4).asSequence()
                .filter {
                    println("filter even $it")
                   it % 2 == 0 //lazy
                }
//                .forEach {
//                    println("only even: $it")
//                }
    }


}