package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class CommandChangeEvent(ts: LocalDateTime) : Event {
	override val name = "command_changed";
	override val ts = ts;
}