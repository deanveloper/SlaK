package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class HelloEvent(ts: LocalDateTime) : Event {
	override val name = "hello";
	override val ts = ts;
}