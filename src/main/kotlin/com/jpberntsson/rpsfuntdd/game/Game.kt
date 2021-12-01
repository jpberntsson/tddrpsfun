package com.jpberntsson.rpsfuntdd.game

import com.jpberntsson.rpsfuntdd.PlayerId

data class Game private constructor(val gameId: GameId, val playerOneId: PlayerId, val playerTwoId: PlayerId, val rounds:List<Round> = ArrayList(1), val liveRound: LiveRound? = null) {

    companion object {
        fun create(gameId: GameId, playerOneId: PlayerId, playerTwoId: PlayerId): Game {
            return Game(gameId, playerOneId, playerTwoId)
        }
    }

    fun playHand(playerId: PlayerId, hand: Hand): Game {
        if(playerId == playerOneId) {
            return registerPlayerOneHand(hand)
        }
        if (playerId == playerTwoId) {
            return registerPlayerTwoHand(hand)
        }
        return this
    }

    fun playerScore(playerId: PlayerId):Int {
        if(playerId == playerOneId) {
            return playerOneScore()
        }
        if(playerId == playerTwoId) {
            return playerTwoScore()
        }
        return 0
    }

    private fun registerPlayerOneHand(hand: Hand): Game {

        if(liveRound?.playerOneHand != null) {
            return this
        }

        if(liveRound?.playerTwoHand == null) {
            return this.copy(liveRound= LiveRound(playerOneHand = hand))
        }

        return registerRound(hand, liveRound.playerTwoHand).copy(liveRound = null)
    }

    private fun registerPlayerTwoHand(hand: Hand): Game {
        if(liveRound?.playerTwoHand != null) {
            return this
        }

        if(liveRound?.playerOneHand == null) {
            return this.copy(liveRound= LiveRound(playerTwoHand = hand))
        }

        return registerRound(liveRound.playerOneHand, hand).copy(liveRound = null)
    }

    private fun playerOneScore():Int {
        return rounds.fold(0) {sum, round -> if(round.result == Round.Result.PLAYER_ONE_WIN)  sum +1 else sum}
    }

    private fun playerTwoScore():Int {
        return rounds.fold(0) {sum, round -> if(round.result == Round.Result.PLAYER_TWO_WIN)  sum+1 else sum}
    }

    private fun registerRound(playerOneHand: Hand, playerTwoHand: Hand): Game {
        val round = Round.generate(playerOneHand, playerTwoHand, ::compareValues)
        val newRounds = rounds.plus(round)
        return this.copy(rounds=newRounds)
    }

    data class LiveRound(val playerOneHand: Hand? = null, val playerTwoHand: Hand? = null)
}