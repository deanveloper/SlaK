package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class EmojiChangeEvent(ts: Instant) : Event {
    override val name = "emoji_changed";
    override val ts = ts;
}