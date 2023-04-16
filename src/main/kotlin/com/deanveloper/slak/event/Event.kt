package com.deanveloper.slak.event

import java.time.LocalDateTime

interface Event {
    val type: String
    val ts: LocalDateTime
}
