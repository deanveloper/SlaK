package com.deanveloper.slackapi.slackapi.event.misc

import com.deanveloper.slackapi.slackapi.event.Event
import java.time.Instant

class DndEvent(ts: Instant) : Event {
    override val name = "dnd_updated";
    override val ts = ts;
}