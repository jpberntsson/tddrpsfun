package com.jpberntsson.rpsfuntdd.aggregate

import com.jpberntsson.rpsfuntdd.PlayerId
import com.jpberntsson.rpsfuntdd.command.CreateGameCommand
import com.jpberntsson.rpsfuntdd.command.PlayHandCommand
import com.jpberntsson.rpsfuntdd.event.Event
import com.jpberntsson.rpsfuntdd.game.GameId
import com.jpberntsson.rpsfuntdd.game.Hand
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameAggregateTest {

    @Test
    fun handle_create_game_test() {
        //Given
        val gameId = GameId.generate()
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val cmd = CreateGameCommand(gameId, playerOneId, playerTwoId)

        //When
        val events = handleCreateGameCommand(cmd, listOf())

        //Then
        assertEquals(1, events.size)
        assertEquals(0, events[0].version)
    }

    @Test
    fun handle_game_created_twice() {
        //Given
        val gameId = GameId.generate()
        val cmd = CreateGameCommand(gameId, PlayerId.generate(), PlayerId.generate())
        val event = Event.GameCreatedEvent(gameId, PlayerId.generate(), PlayerId.generate())

        //When
        val events = handleCreateGameCommand(cmd, listOf(event))

        //Then
        assertEquals(0, events.size)
    }

    @Test
    fun create_game_play_two_rounds() {
        //Given
        val gameId = GameId.generate()
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val createGameCmd = CreateGameCommand(gameId, playerOneId, playerTwoId)
        val playHand1Cmd_1 = PlayHandCommand(gameId, playerOneId, Hand.Rock)
        val playHand1Cmd_2 = PlayHandCommand(gameId, playerTwoId, Hand.Paper)
        val playHand2Cmd_1 = PlayHandCommand(gameId, playerOneId, Hand.Rock)
        val playHand2Cmd_2 = PlayHandCommand(gameId, playerTwoId, Hand.Paper)

        //When
        var events = handleCreateGameCommand(createGameCmd, listOf())
        events = events + handlePlayHandCommand(playHand1Cmd_1, events)
        events = events + handlePlayHandCommand(playHand1Cmd_2, events)
        events = events + handlePlayHandCommand(playHand2Cmd_1, events)
        events = events + handlePlayHandCommand(playHand2Cmd_2, events)
        val game = recreateGameState(gameId, events)

        //Then
        assertEquals(5, events.size)
        assertEquals(2, game?.playerScore(playerTwoId))
        assertEquals(0, game?.playerScore(playerOneId))

        assertEquals(0, events[0].version)
        assertEquals(1, events[1].version)
        assertEquals(2, events[2].version)
        assertEquals(3, events[3].version)
        assertEquals(4, events[4].version)
    }

    @Test
    fun create_game_play_one_round_with_event_from_wrong_game() {
        //Given
        val gameId = GameId.generate()
        val secondGameId = GameId.generate()
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val createGameCmd = CreateGameCommand(gameId, playerOneId, playerTwoId)
        val playHand1Cmd_1 = PlayHandCommand(gameId, playerOneId, Hand.Rock)
        val playHand1Cmd_3 = PlayHandCommand(secondGameId, playerTwoId, Hand.Scissors)
        val playHand1Cmd_2 = PlayHandCommand(gameId, playerTwoId, Hand.Paper)

        //When
        var events = handleCreateGameCommand(createGameCmd, listOf())
        events = events + handlePlayHandCommand(playHand1Cmd_1, events)
        events = events + handlePlayHandCommand(playHand1Cmd_3, events)
        events = events + handlePlayHandCommand(playHand1Cmd_2, events)
        val game = recreateGameState(gameId, events)

        //Then
        assertEquals(3, events.size)
        assertEquals(1, game?.playerScore(playerTwoId))
        assertEquals(0, game?.playerScore(playerOneId))
    }

    @Test
    fun create_game_play_hand_twice_for_player() {
        //Given
        val gameId = GameId.generate()
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        val createGameCmd = CreateGameCommand(gameId, playerOneId, playerTwoId)
        val playHand1Cmd_1 = PlayHandCommand(gameId, playerOneId, Hand.Rock)

        //When
        var events = handleCreateGameCommand(createGameCmd, listOf())
        events = events + handlePlayHandCommand(playHand1Cmd_1, events)
        events = events + handlePlayHandCommand(playHand1Cmd_1, events)
        val game = recreateGameState(gameId, events)

        //Then
        assertEquals(2, events.size)
        assertEquals(0, game?.playerScore(playerOneId))
        assertEquals(0, game?.playerScore(playerTwoId))
    }
}