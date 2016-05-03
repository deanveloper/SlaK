package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class MessageEvent(user: User, ts: LocalDateTime) : UserEvent {
	override val name = "message"
	override val ts = ts
	override val user = user
}