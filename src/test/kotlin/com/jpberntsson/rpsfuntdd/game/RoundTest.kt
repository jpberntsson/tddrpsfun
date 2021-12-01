package com.jpberntsson.rpsfuntdd.game

import com.jpberntsson.rpsfuntdd.game.Hand
import com.jpberntsson.rpsfuntdd.game.Round
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RoundTest {

    @Test
    fun finish_round_player2_winner() {
        val handEvaluator = { hand1: Hand, hand2: Hand ->
            //Return bad result if parameters are not sent in correctly
            if(hand1 == Hand.Rock && hand2 == Hand.Paper) {
                -1
            } else {
                1
            }
        }
        val finishedRound = Round.generate(Hand.Rock, Hand.Paper, handEvaluator)

        assertEquals(Hand.Rock, finishedRound.player1Hand)
        assertEquals(Hand.Paper, finishedRound.player2Hand)
        assertEquals(Round.Result.PLAYER_TWO_WIN, finishedRound.result)
    }

    @Test
    fun finish_round_player1_winner() {
        val handEvaluator = { hand1: Hand, hand2: Hand ->
            //Return bad result if parameters are not sent in correctly
            if(hand1 == Hand.Rock && hand2 == Hand.Paper) {
                1
            } else {
                -1
            }
        }
        val finishedRound = Round.generate(Hand.Rock, Hand.Paper, handEvaluator)


        assertEquals(Hand.Rock, finishedRound.player1Hand)
        assertEquals(Hand.Paper, finishedRound.player2Hand)
        assertEquals(Round.Result.PLAYER_ONE_WIN, finishedRound.result)

    }

    @Test
    fun finish_round_draw() {
        val handEvaluator = { hand1: Hand, hand2: Hand ->
            //Return bad result if parameters are not sent in correctly
            if(hand1 == Hand.Rock && hand2 == Hand.Paper) {
                0
            } else {
                1
            }
        }
        val finishedRound = Round.generate(Hand.Rock, Hand.Paper, handEvaluator)

        assertEquals(Hand.Rock, finishedRound.player1Hand)
        assertEquals(Hand.Paper, finishedRound.player2Hand)
        assertEquals(Round.Result.DRAW, finishedRound.result)
    }
}