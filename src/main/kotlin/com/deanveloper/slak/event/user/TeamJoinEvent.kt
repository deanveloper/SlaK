package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class TeamJoinEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "team_join"
	override val ts = ts
	override val user = user
}