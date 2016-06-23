package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class HelloEvent(ts: LocalDateTime) : Event {
    override val type = "hello"
    override val ts = ts
}
