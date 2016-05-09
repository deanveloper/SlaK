package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class ManualPresenceChangedEvent(ts: LocalDateTime) : Event {
    override val name = "manual_presence_changed"
    override val ts = ts
}
