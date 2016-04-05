package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class UserDndEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "dnd_updated_user";
	override val ts = ts;
	override val user = user;
}