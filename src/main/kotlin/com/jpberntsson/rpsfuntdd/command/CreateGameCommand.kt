package com.jpberntsson.rpsfuntdd.command

import com.jpberntsson.rpsfuntdd.PlayerId
import com.jpberntsson.rpsfuntdd.game.GameId

data class CreateGameCommand(val gameId: GameId, val playerOneId:PlayerId, val playerTwoId: PlayerId) {
}