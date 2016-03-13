package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class PrefrencesChangedEvent(ts: Instant) : Event {
    override val name = "pref_change";
    override val ts = ts;
}