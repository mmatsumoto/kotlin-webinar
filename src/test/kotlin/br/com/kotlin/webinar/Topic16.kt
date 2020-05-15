@file:Suppress("UNUSED_VARIABLE", "unused")

package br.com.kotlin.webinar

import br.com.kotlin.webinar.GameType.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


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

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class Topic16 {

    private val totalWar = Game("Total War Rome II",
                                                                         STRATEGY)


    // also: additional processing on an object in a call chain
    @Test
    fun `also do this with the object`() {

        val gameT = totalWar
                .also { println(it.name) }
                .also { it.startGame() }

        assertEquals(totalWar, gameT)


        // tacit programming, no point-free style
        val someGame = getGameInfo()
        val players = getPlayers(someGame)
        val records = updateResults(someGame, players)
        displayResults(records)

        getGameInfo()
                .let { game -> Pair(game, getPlayers(game)) }
                .let { (game, players) -> updateResults(game, players) }
                .also { displayResults(it) }


    }


    //let: conversion of value
    @Test
    fun `let looks like map`() {

        val result = totalWar
                .also { println(it.name) }
                .let {
                    GameResult(it)
                }


        val gameResult =
                Game("Total War Rome II", STRATEGY)
                        .let {
                            println("game $it")
                            GameResult(it) // return
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

        /* java

      Game game = new Game()
      game.setType(..)
      game.setVendor(..)
      game.initSomething()

    */


        val warThunder = Game("War Thunder")
        val game = warThunder.apply {
            type = FS
            vendor = "Gaijin"
            initSomething()
        }



        assertEquals(warThunder, game)
        assertEquals(FS, game.type)
    }

    // run: It is used with lambdas that do not return values, but rather just create some side-effects:
    @Test
    fun `run with lambdas that do not return values`() {

        val warThunder: Game? =
                Game("War Thunder", FS)


        warThunder?.run {
            println(type)
        }

    }

    //    with: configure objects created somewhere else, should be used to call multiple methods on an object.
    @Test
    fun `with should be used to call multiple methods on an object`() {


        val result = with(totalWar) {
            clear()
            loadPlayers()
            startGame()
            "foobar"
        }

        assertEquals("foobar", result)
    }

}

private fun displayResults(records: List<PlayerHistory>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private fun updateResults(game: Game, players: List<Player>): List<PlayerHistory> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private fun getPlayers(game: Game): List<Player> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private fun getGameInfo(): Game {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


enum class GameType {
    MMO, STRATEGY, FS
}

data class Player(val name: String)
data class PlayerHistory(val player: Player)

data class GameResult(val game: Game)


data class Game(var name: String, var type: GameType? = null, var vendor: String? = null) {
    fun startGame() {
        println("game is starting")
    }

    internal fun initSomething() {
    }

    fun clear() {
    }

    fun loadPlayers() {
    }
}

