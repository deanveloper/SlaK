package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.Instant

class PrefrencesChangedEvent(ts: Instant) : Event {
	override val name = "pref_change";
	override val ts = ts;
}