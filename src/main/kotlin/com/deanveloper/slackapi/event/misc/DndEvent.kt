package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class DndEvent(ts: LocalDateTime) : Event {
	override val name = "dnd_updated";
	override val ts = ts;
}