package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.slackapi.event.Event
import java.time.Instant

class ManualPresenceChangedEvent(ts: Instant) : Event {
    override val name = "manual_presence_changed";
    override val ts = ts;
}