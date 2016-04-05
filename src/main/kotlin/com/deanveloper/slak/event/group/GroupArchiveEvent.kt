package com.deanveloper.slak.event.group

import com.deanveloper.slak.channel.Group
import java.time.LocalDateTime

class GroupArchiveEvent(group: Group, ts: LocalDateTime) : GroupEvent {
	override val name = "group_archive";
	override val group = group;
	override val ts = ts;
}