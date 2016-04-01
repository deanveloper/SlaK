package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class UserTypingEvent(user: User, ts: Instant) : UserEvent {
	override val name = "user_typing";
	override val ts = ts;
	override val user = user;
}