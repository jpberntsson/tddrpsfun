package com.jpberntsson.rpsfuntdd

data class GameLobby private constructor (val initialPlayerId: PlayerId, val players:Set<PlayerId> = setOf(initialPlayerId)) {


    companion object {
        fun create(playerId: PlayerId):GameLobby {
            return GameLobby(playerId)
        }
    }

    fun addPlayer(playerId: PlayerId):GameLobby {
        val updatedPlayerSet = players.toMutableSet()
        updatedPlayerSet.add(playerId)
        return this.copy(players = updatedPlayerSet.toSet())
    }
}