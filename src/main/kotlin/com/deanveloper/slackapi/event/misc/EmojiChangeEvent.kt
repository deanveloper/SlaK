package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.Instant

class EmojiChangeEvent(ts: Instant) : Event {
    override val name = "emoji_changed";
    override val ts = ts;
}