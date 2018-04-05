package br.com.zup.kotlin

import br.com.zup.kotlin.GameType.FS
import br.com.zup.kotlin.GameType.STRATEGY
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

enum class GameType {
    MMO, STRATEGY, FS
}

data class GameResult(val game: Game)

data class Game(var name: String, var type: GameType? = null) {
    fun startGame() {
        println("game is starting")
    }
}


/**
    PARAMETER	    SAME	    DIFFERENT
    it	            also	    let
    this	        apply	    run, with


    also: additional processing on an object in a call chain
    let: conversion of value
    apply: post-construction configuration
    run: execute lambdas with side-effects and no result
    with: configure objects created somewhere else
 */
class Topic16 {

    val totalWar = Game("Total War Rome II", STRATEGY)

    // also: additional processing on an object in a call chain
    @Test
    fun `also do this with the object`() {
        val game = totalWar
                .also { println(it.name) }
                .also { it.startGame() }

        assertEquals(totalWar, game)
    }


    //let: conversion of value
    @Test
    fun `let looks like map`() {

        val result = totalWar
                .also { println(it.name) }
                .let {
                    "asdfasdf"
                    GameResult(it)
                }

        assertEquals(GameResult(totalWar), result)

        totalWar.type?.let {
            assertNotNull(it)
            assertEquals(STRATEGY, it)
        }

    }

    //apply: post-construction configuration
    @Test
    fun `apply is used for post-construction configuration`() {

        val warThunder = Game("War Thunder")

        val result = warThunder.apply {
            type = FS
        }

        assertEquals(warThunder, result)
        assertEquals(FS, result.type)
    }

    // run: execute lambdas with side-effects and no result
    @Test
    fun `run with lambdas that do not return values`() {

        val type = Game("War Thunder", FS)
                .run { this.type }

        assertEquals(FS, type)
    }

    //    with: configure objects created somewhere else
    @Test
    fun `with should be used to call multiple methods on an object`() {
        val result = with (totalWar) {
            this.startGame()
            startGame()
            // other Game method
            "foobar"
        }

        assertEquals("foobar", result)
    }

}

