package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.Instant

class CommandChangeEvent(ts: Instant) : Event {
    override val name = "command_changed";
    override val ts = ts;
}