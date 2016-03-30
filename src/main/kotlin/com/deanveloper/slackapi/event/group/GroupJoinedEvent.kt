package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.slackapi.channel.Group
import java.time.Instant

class GroupJoinedEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_joined";
    override val group = group;
    override val ts = ts;
}