package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class TeamJoinEvent(user: User, ts: Instant) : UserEvent {
	override val name = "team_join";
	override val ts = ts;
	override val user = user;
}