package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.Instant

class DndEvent(ts: Instant) : Event {
    override val name = "dnd_updated";
    override val ts = ts;
}