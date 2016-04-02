package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

class ReactionRemovedEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "reaction_removed";
	override val ts = ts;
	override val user = user;
}