@file:Suppress("UNUSED_VARIABLE", "unused")

package br.com.zup.kotlin

import br.com.zup.kotlin.GameType.*
import br.com.zup.zkotlin.either.Either
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

    private val totalWar = Game("Total War Rome II", STRATEGY)


    // also: additional processing on an object in a call chain
    @Test
    fun `also do this with the object`() {

        val game = totalWar
                .also { println(it.name) }
                .also { it.startGame() }

        assertEquals(totalWar, game)

        val gameRest = GameRest()

//        val game = getGameInfo()
//        val players = getPlayers(game)
//        val records = updateResults(game, players)
//        displayResults(records)

//        getGameInfo()
//                .let { Pair(it, getPlayers(it)) }
//                .let { (game, players) -> updateResults(game, players) }
//                .also { displayResults(it) }


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

        val warThunder: Game? = Game("War Thunder", FS)


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

class GameRest {
    fun getGame() = Game("any", MMO)

//    fun getGames(page: PageNumber): List<Game>  {
//        TODO()
//    }

    fun getGame(id: GameId): Either<Failure, Success<Game>> {
        TODO()
    }

    fun getGames(page: PageNumber): Either<Failure, List<Game>> {
        TODO()
    }

    fun getPlayers(game: Game): Either<Failure, Success<GamePlayer>> {
        TODO()
    }

    fun updateResults(gamePlayer: GamePlayer): Either<Failure, Success<GameResult>> {
        TODO()
    }

    fun getPlayers(game: Failure): Either<Failure, Success<GamePlayer>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateResults(gamePlayer: Failure): Either<Failure, Success<GameResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun createNewGame(): Either<Failure, Success<Game>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

data class Success<T>(val result: T)

data class GamePlayer(val game: Game, val players: List<Player>)

typealias GameId = Long

class Failure(message: String) : RuntimeException(message)

typealias PageNumber = Int
