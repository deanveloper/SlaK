package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class ManualPresenceChangedEvent(ts: Instant) : Event {
    override val name = "manual_presence_changed";
    override val ts = ts;
}