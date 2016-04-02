package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

class UserTypingEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "user_typing";
	override val ts = ts;
	override val user = user;
}