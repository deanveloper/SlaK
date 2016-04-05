package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class PrefrencesChangedEvent(ts: LocalDateTime) : Event {
	override val name = "pref_change";
	override val ts = ts;
}