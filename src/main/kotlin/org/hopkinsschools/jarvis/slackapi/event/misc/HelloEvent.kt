package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class HelloEvent(ts: Instant) : Event {
    override val name = "hello";
    override val ts = ts;
}