//package br.com.zup.kotlin
//
//import br.com.zup.zkotlin.currying.curried
//import org.junit.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertTrue
//
//class Topic20 {
//
//    private val numbers = (1..10)
//
//    private fun multiply(x: Int, y: Int) = x * y
//
//    private fun isDivisible(d: Int, n: Int) = n % d == 0
//
//    @Test
//    fun noCurry() {
//        assertEquals(listOf(4, 8, 12, 16, 20),
//                     numbers
//                             .filter { isDivisible(2, it) }
//                             .map { multiply(2, it) }
//                    )
//    }
//
//    @Test
//    fun filterMapWithCurried() {
//
//        val divisibleCurried = ::isDivisible.curried()
//        val multiplyCurried = ::multiply.curried()
//
//        val evenNumberOnly = divisibleCurried(2)
//        val doubleIt = multiplyCurried(2)
//
//
//        assertEquals(listOf(4, 8, 12, 16, 20),
//                     numbers
//                             .filter(evenNumberOnly)
//                             .map(doubleIt))
//
//    }
//
//    @Test
//    fun curryingWithExtensionMethod() {
//        val divisibleCurried = ::isDivisible.curried()
//
//        assertTrue(divisibleCurried(2)(4))
//        assertFalse(divisibleCurried(4)(3))
//
//
//        val multiplyCurried = ::multiply.curried()
//        assertEquals(4, multiplyCurried(2)(2))
//        assertEquals(6, multiplyCurried(2)(3))
//    }
//
//
//    private fun divisibleCurriedByYourself(d: Int): (n: Int) -> Boolean =
//            { n -> n % d == 0 }
//
//    private fun multiplyCurried(x: Int): (y: Int) -> Int =
//             { y -> x * y }
//
//    @Test
//    fun curryingByYourSelf() {
//
//        assertTrue(divisibleCurriedByYourself(2)(4))
//        assertFalse(divisibleCurriedByYourself(4)(3))
//
//        assertEquals(4, multiplyCurried(2)(2))
//        assertEquals(6, multiplyCurried(2)(3))
//    }
//
//
//}
