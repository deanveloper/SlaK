package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.slackapi.event.Event
import java.time.Instant

class HelloEvent(ts: Instant) : Event {
    override val name = "hello";
    override val ts = ts;
}