package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.channel.Group
import java.time.Instant

class GroupLeftEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_left";
    override val group = group;
    override val ts = ts;
}