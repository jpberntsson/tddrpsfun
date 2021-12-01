package com.jpberntsson.rpsfuntdd.game

import java.util.*

data class GameId(val id: UUID) {
    companion object {
        fun generate():GameId = GameId(UUID.randomUUID())
    }
}