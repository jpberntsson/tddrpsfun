package com.jpberntsson.rpsfuntdd.game

class Round(val player1Hand: Hand, val player2Hand: Hand, private val handEvaluator:(Hand, Hand) -> Int)  {
    private val nbr = handEvaluator(player1Hand, player2Hand)

    val result = if(nbr > 0) {
        Result.PLAYER_ONE_WIN
    } else if (nbr == 0) {
        Result.DRAW
    } else {
        Result.PLAYER_TWO_WIN
    }

    companion object {
        fun generate(player1Hand: Hand, player2Hand: Hand, handEvaluator:(Hand, Hand) -> Int): Round {
            return Round(player1Hand, player2Hand, handEvaluator)
        }
    }

    enum class Result {
        DRAW,
        PLAYER_ONE_WIN,
        PLAYER_TWO_WIN
    }

}