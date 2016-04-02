package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.channel.Group
import java.time.LocalDateTime

class GroupMarkedEvent(group: Group, ts: LocalDateTime) : GroupEvent {
	override val name = "group_marked";
	override val group = group;
	override val ts = ts;
}