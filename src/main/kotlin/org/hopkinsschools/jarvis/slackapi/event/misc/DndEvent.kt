package org.hopkinsschools.jarvis.slackapi.event

import java.time.Instant

class DndEvent(ts: Instant) : Event {
    override val name = "dnd_updated";
    override val ts = ts;
}