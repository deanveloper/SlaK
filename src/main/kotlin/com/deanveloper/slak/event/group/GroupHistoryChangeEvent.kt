package com.deanveloper.slak.event.group

import com.deanveloper.slak.channel.Group
import java.time.LocalDateTime

class GroupHistoryChangeEvent(group: Group, ts: LocalDateTime) : GroupEvent {
	override val name = "group_history_changed"
	override val group = group
	override val ts = ts
}