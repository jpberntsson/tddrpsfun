package com.jpberntsson.rpsfuntdd.game

import com.jpberntsson.rpsfuntdd.PlayerId
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameTest {
    @Test
    fun create_game() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val gameId = GameId.generate()

        //When
        val game = Game.create(gameId,playerOneId, playerTwoId)

        //Then
        assertEquals(gameId, game.gameId)
        assertEquals(playerOneId, game.playerOneId)
        assertEquals(playerTwoId, game.playerTwoId)
    }

    @Test
    fun play_one_round() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        var game = Game.create(GameId.generate(), playerOneId, playerTwoId)

        //When
        game = game.playHand(playerOneId, Hand.Rock)
        game = game.playHand(playerTwoId, Hand.Paper)

        //Then
        assertEquals(1, game.rounds.size)
        assertEquals(0, game.playerScore(playerOneId))
        assertEquals(1, game.playerScore(playerTwoId))
    }

    @Test
    fun play_two_rounds() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        var game = Game.create(GameId.generate(), playerOneId, playerTwoId)

        //When
        game = game.playHand(playerOneId, Hand.Rock)
        game = game.playHand(playerTwoId, Hand.Paper)
        game = game.playHand(playerOneId, Hand.Rock)
        game = game.playHand(playerTwoId, Hand.Paper)

        //Then
        assertEquals(2, game.rounds.size)
        assertEquals(0, game.playerScore(playerOneId))
        assertEquals(2, game.playerScore(playerTwoId))
    }

    @Test
    fun play_one_round_player_one_plays_hand_twice() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        var game = Game.create(GameId.generate(), playerOneId, playerTwoId)

        //When
        game = game.playHand(playerOneId, Hand.Rock)
        game = game.playHand(playerOneId, Hand.Scissors)
        game = game.playHand(playerTwoId, Hand.Paper)

        //Then
        assertEquals(1, game.rounds.size)
        assertEquals(0, game.playerScore(playerOneId))
        assertEquals(1, game.playerScore(playerTwoId))
    }

    @Test
    fun play_one_round_3rd_player_tries_to_play_hand() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val playerThreeId = PlayerId.generate()
        var game = Game.create(GameId.generate(), playerOneId, playerTwoId)

        //When
        game = game.playHand(playerOneId, Hand.Rock)
        game = game.playHand(playerThreeId, Hand.Scissors)
        game = game.playHand(playerTwoId, Hand.Paper)

        //Then
        assertEquals(1, game.rounds.size)
        assertEquals(0, game.playerScore(playerOneId))
        assertEquals(1, game.playerScore(playerTwoId))
    }
}