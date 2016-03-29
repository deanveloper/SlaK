package org.hopkinsschools.jarvis.slackapi.event.group

import org.hopkinsschools.jarvis.slackapi.channel.Group
import java.time.Instant

class GroupJoinedEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_joined";
    override val group = group;
    override val ts = ts;
}