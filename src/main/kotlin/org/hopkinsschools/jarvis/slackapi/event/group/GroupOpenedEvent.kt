package org.hopkinsschools.jarvis.slackapi.event.group

import org.hopkinsschools.jarvis.slackapi.channel.Group
import java.time.Instant

class GroupOpenedEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_open";
    override val group = group;
    override val ts = ts;
}