package com.jpberntsson.rpsfuntdd.event

import java.util.*

data class EventId private constructor(val id: UUID) {
    companion object {
        fun generate():EventId = EventId(UUID.randomUUID())
    }
}