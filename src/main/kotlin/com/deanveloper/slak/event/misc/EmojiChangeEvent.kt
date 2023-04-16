package com.deanveloper.slak.event.misc

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class EmojiChangeEvent(ts: LocalDateTime) : Event {
    override val type = "emoji_changed"
    override val ts = ts
}
