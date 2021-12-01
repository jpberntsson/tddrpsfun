package com.jpberntsson.rpsfuntdd.service

import com.jpberntsson.rpsfuntdd.PlayerId
import org.junit.jupiter.api.Test

class GameServiceTest {
    @Test
    fun test_createGame() {
        // Given

        //TODO add mockk
        val gameService = GameService()
        val playerOneId = PlayerId.generate()
        val playerTwoId = PlayerId.generate()

        // When
        gameService.createGame(playerOneId, playerTwoId)

        // Then

    }
}