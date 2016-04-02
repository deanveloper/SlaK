package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

class ReactionAddedEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "reaction_added";
	override val ts = ts;
	override val user = user;
}