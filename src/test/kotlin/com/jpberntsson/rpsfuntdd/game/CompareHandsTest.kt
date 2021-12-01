package com.jpberntsson.rpsfuntdd.game

import com.jpberntsson.rpsfuntdd.game.Hand
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CompareHandsTest {
    @Test
    fun rock_beats_scissors() {
        assertEquals(1, compareValues(Hand.Rock, Hand.Scissors))
    }

    @Test
    fun rock_ties_rock() {
        assertEquals(0, compareValues(Hand.Rock, Hand.Rock))
    }

    @Test
    fun rock_loses_to_paper() {
        assertEquals(-1, compareValues(Hand.Rock, Hand.Paper))
    }

    @Test
    fun scissors_beats_paper() {
        assertEquals(1, compareValues(Hand.Scissors, Hand.Paper))
    }

    @Test
    fun scissors_ties_scissors() {
        assertEquals(0, compareValues(Hand.Scissors, Hand.Scissors))
    }

    @Test
    fun scissors_loses_to_rock() {
        assertEquals(-1, compareValues(Hand.Scissors, Hand.Rock))
    }

    @Test
    fun paper_beats_rock() {
        assertEquals(1, compareValues(Hand.Paper, Hand.Rock))
    }

    @Test
    fun paper_ties_paper() {
        assertEquals(0, compareValues(Hand.Paper, Hand.Paper))
    }

    @Test
    fun paper_loses_to_scissors() {
        assertEquals(-1, compareValues(Hand.Paper, Hand.Scissors))
    }
}