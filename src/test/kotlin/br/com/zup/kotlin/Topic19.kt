package br.com.zup.kotlin

import br.com.zup.zkotlin.either.Either
import java.util.logging.Level
import java.util.logging.Logger


class Topic19 {

    val gameRest = GameRest()
    val gameRestV1 = GameRestCallback()
    val backupRest = GameRest()

    val logger = Logger.getLogger(this.javaClass.name)


    fun get(page: PageNumber, callback: Callback<List<Game>>) {
        try {
            val games = gameRestV1.getGames(page)
            callback.onSuccess(games)
        } catch (e: Exception) {
            logger.error(e)
            callback.onError(e.message)
        }
    }

    fun get(page: PageNumber): List<Game> {

        val result = gameRest.getGames(page)

        result.isLeft()
        result.isRight()
        result.get()
        result.failure()
        result.success {
        }
        result.failure {
        }

        return result.fold({
                               emptyList()
                           },
                           {
                               it
                           })
    }


    fun updateWithCallBackHell(gameId: GameId) {
        gameRest.getGame(gameId).fold(
                { logger.error(it) },
                { game ->
                    gameRest.getPlayers(game.result).fold(
                            { logger.error(it) },
                            {
                                gameRest.updateResults(it.result).fold(
                                        { logger.error(it) },
                                        { result ->
                                            result.also {
                                                logger.info("finally!")
                                            }
                                        })
                            })
                })

    }

    fun noFoldHellWithFoldCompose(gameId: GameId) {
        gameRest.getGame(gameId)
                .foldCompose({ backupRest.getPlayers(it) }, { gameRest.getPlayers(it.result) })
                .foldCompose({ backupRest.updateResults(it) }, { gameRest.updateResults(it.result) })
                .fold({ logger.error(it) }, {
                    // success
                })


    }

    fun noFoldHellWithAndThen(gameId: GameId) {
        gameRest.getGame(gameId)
                .andThen { gameRest.getPlayers(it.result) }
                .andThen { gameRest.updateResults(it.result) }
                .fold({ logger.error(it) }, {
                    // success
                })

    }

    fun noFoldHellWithgetOrElseAndThen(gameId: GameId) {
        gameRest.getGame(gameId)
                .getOrElse { backupRest.getGame(gameId) }
                .getOrElse { backupRest.createNewGame() }
                .andThen { gameRest.getPlayers(it.result) }
                .andThen { gameRest.updateResults(it.result) }
                .fold({ logger.error(it) }, {
                    // success
                })
    }

}

class Callback<T> {
    fun onSuccess(result: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onError(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

private fun Logger.error(e: Exception) {
    log(Level.SEVERE, "error", e)
}




class GameRestCallback {
    fun getGames(page: PageNumber): List<Game> {
        TODO()
    }
}

class GameRest {
    fun getGame() = Game("any", GameType.MMO)

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