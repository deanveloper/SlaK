package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class UserDndEvent(user: User, ts: Instant) : UserEvent {
	override val name = "dnd_updated_user";
	override val ts = ts;
	override val user = user;
}