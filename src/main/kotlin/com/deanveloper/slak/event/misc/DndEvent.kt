package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class DndEvent(ts: LocalDateTime) : Event {
	override val name = "dnd_updated"
	override val ts = ts
}