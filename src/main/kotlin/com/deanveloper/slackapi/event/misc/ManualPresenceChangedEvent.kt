package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class ManualPresenceChangedEvent(ts: LocalDateTime) : Event {
	override val name = "manual_presence_changed";
	override val ts = ts;
}