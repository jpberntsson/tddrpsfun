package com.jpberntsson.rpsfuntdd

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameLobbyTest {

    @Test
    fun create_lobby_test() {

        //Given
        val playerId = PlayerId.generate()

        //When
        val lobby = GameLobby.create(playerId)

        //Then
        assertTrue(lobby.players.contains(playerId))
    }

    @Test
    fun add_second_player_to_lobby() {
        //Given
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()
        var lobby = GameLobby.create(playerOneId)

        //When
        lobby = lobby.addPlayer(playerTwoId)

        //Then
        assertTrue(lobby.players.contains(playerOneId))
        assertTrue(lobby.players.contains(playerTwoId))
    }

    @Test
    fun add_player_twice_to_lobby() {
        //Given
        val playerOneId = PlayerId.generate()
        var lobby = GameLobby.create(playerOneId)

        //When
        lobby = lobby.addPlayer(playerOneId)

        //Then
        assertEquals(1, lobby.players.size)
    }
}