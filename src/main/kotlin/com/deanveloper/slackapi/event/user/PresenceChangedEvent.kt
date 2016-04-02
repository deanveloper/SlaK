package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

class PresenceChangedEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "presence_changed";
	override val ts = ts;
	override val user = user;
}