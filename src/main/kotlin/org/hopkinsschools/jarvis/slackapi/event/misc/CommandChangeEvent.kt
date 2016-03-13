package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class CommandChangeEvent(ts: Instant) : Event {
    override val name = "command_changed";
    override val ts = ts;
}