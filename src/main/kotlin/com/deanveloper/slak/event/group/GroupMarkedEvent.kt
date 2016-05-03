package com.deanveloper.slak.event.group

import com.deanveloper.slak.channel.Group
import java.time.LocalDateTime

class GroupMarkedEvent(group: Group, ts: LocalDateTime) : GroupEvent {
	override val name = "group_marked"
	override val group = group
	override val ts = ts
}