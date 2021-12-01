package com.jpberntsson.rpsfuntdd.event

import com.jpberntsson.rpsfuntdd.PlayerId
import com.jpberntsson.rpsfuntdd.game.GameId
import com.jpberntsson.rpsfuntdd.game.Hand
import java.util.*

sealed class Event(val streamId: UUID, val version:Long = 0) {
    val eventId = EventId.generate()

    class GameCreatedEvent(gameId: GameId, val playerOneId: PlayerId, val playerTwoId:PlayerId):Event(gameId.id)
    class HandPlayedEvent(gameId: GameId, val playerId: PlayerId, val hand: Hand, version: Long = 0):Event(gameId.id, version)
}
