package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

class UserDndEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "dnd_updated_user";
	override val ts = ts;
	override val user = user;
}