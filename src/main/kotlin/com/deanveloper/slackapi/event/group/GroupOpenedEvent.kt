package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.channel.Group
import java.time.LocalDateTime

class GroupOpenedEvent(group: Group, ts: LocalDateTime) : GroupEvent {
	override val name = "group_open";
	override val group = group;
	override val ts = ts;
}