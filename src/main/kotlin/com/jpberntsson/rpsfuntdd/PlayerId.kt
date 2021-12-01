package com.jpberntsson.rpsfuntdd

import java.util.*

data class PlayerId private constructor(val id:UUID) {
    companion object {
        fun generate():PlayerId = PlayerId(UUID.randomUUID())
    }
}