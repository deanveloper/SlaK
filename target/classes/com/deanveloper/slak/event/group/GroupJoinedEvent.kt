package com.deanveloper.slak.event.group

import com.deanveloper.slak.channel.Group
import java.time.LocalDateTime

class GroupJoinedEvent(group: Group, ts: LocalDateTime) : GroupEvent {
    override val type = "group_joined"
    override val group = group
    override val ts = ts
}
