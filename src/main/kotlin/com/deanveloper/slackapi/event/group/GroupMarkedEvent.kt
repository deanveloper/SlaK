package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.slackapi.channel.Group
import java.time.Instant

class GroupMarkedEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_marked";
    override val group = group;
    override val ts = ts;
}