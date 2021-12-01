package com.jpberntsson.rpsfuntdd.game


sealed class Hand:Comparable<Hand> {

    override fun compareTo(other: Hand): Int {
        if(other == this) {
            return 0
        }
        when(this) {
            is Rock -> {
                if(other == Scissors) {
                    return 1
                }
            }
            is Paper -> {
                if(other == Rock) {
                    return 1;
                }
            }
            is Scissors -> {
                if(other == Paper) {
                    return 1;
                }
            }
        }
        return -1;
    }

    object Rock : Hand() {

    }
    object Paper: Hand() {

    }
    object Scissors: Hand() {

    }
}