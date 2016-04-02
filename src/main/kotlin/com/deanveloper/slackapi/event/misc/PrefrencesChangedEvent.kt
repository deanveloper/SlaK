package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class PrefrencesChangedEvent(ts: LocalDateTime) : Event {
	override val name = "pref_change";
	override val ts = ts;
}