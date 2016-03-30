package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.channel.Group
import java.time.Instant

class GroupHistoryChangeEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_history_changed";
    override val group = group;
    override val ts = ts;
}