package com.jpberntsson.rpsfuntdd.aggregate

import com.jpberntsson.rpsfuntdd.command.CreateGameCommand
import com.jpberntsson.rpsfuntdd.command.PlayHandCommand
import com.jpberntsson.rpsfuntdd.event.Event
import com.jpberntsson.rpsfuntdd.game.Game
import com.jpberntsson.rpsfuntdd.game.GameId

fun handleCreateGameCommand(gameCommand: CreateGameCommand, events:List<Event>):List<Event> {
    val game = recreateGameState(gameCommand.gameId, events)
    return if(game == null) {listOf(Event.GameCreatedEvent(gameCommand.gameId, gameCommand.playerOneId, gameCommand.playerTwoId))} else {
        emptyList()}
}

fun recreateGameState(gameId: GameId, events:List<Event>, snapShot:Game? = null): Game? {
    return events
            .filter { event -> event.streamId == gameId.id}
            .fold(snapShot) { game, event -> nextGameState(game, event) }
}

fun nextGameState(game:Game?, event:Event):Game? {
    return when(event) {
        is Event.GameCreatedEvent -> game ?: Game.create(GameId(event.streamId), event.playerOneId, event.playerTwoId)
        is Event.HandPlayedEvent -> game?.playHand(event.playerId, event.hand) ?: null
    }
}

fun handlePlayHandCommand(playHandCmd: PlayHandCommand, events: List<Event>):List<Event> {
    val game = recreateGameState(playHandCmd.gameId, events)
    val updatedGame = game?.playHand(playHandCmd.playerOneId, playHandCmd.hand)

    /*
     If game == null it means that no CreateGameEvent for a game
     with the gameId in the command was found.

     If game == updatedGame that means that the state hasn't changed and no event
     needs to be omitted
     */

    if(game == null || updatedGame == null || (game == updatedGame)) {
        return emptyList()
    }

    val versionNbr = events.lastOrNull()?.version?.plus(1) ?: 0
    return listOf(Event.HandPlayedEvent(playHandCmd.gameId, playHandCmd.playerOneId, playHandCmd.hand, versionNbr))
}
