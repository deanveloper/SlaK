package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import java.time.LocalDateTime

class Group(
		id: String,
		name: String,
		created: LocalDateTime,
		archived: Boolean,
		general: Boolean,
		members: List<User>?,
		topic: OwnedString.Topic,
		purpose: OwnedString.Purpose
) : BaseChannel<Group>(id, name, created, archived, general, members, topic, purpose) {
	init {
		TODO()
	}
}