package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class CommandChangeEvent(ts: LocalDateTime) : Event {
    override val type = "command_changed"
    override val ts = ts
}
