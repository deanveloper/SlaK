package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class EmojiChangeEvent(ts: LocalDateTime) : Event {
	override val name = "emoji_changed";
	override val ts = ts;
}