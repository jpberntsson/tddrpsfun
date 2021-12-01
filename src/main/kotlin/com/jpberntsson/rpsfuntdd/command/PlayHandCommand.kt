package com.jpberntsson.rpsfuntdd.command

import com.jpberntsson.rpsfuntdd.PlayerId
import com.jpberntsson.rpsfuntdd.game.GameId
import com.jpberntsson.rpsfuntdd.game.Hand

data class PlayHandCommand(val gameId: GameId, val playerOneId: PlayerId, val hand: Hand)