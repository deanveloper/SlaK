package org.hopkinsschools.jarvis.slackapi.event.group

import org.hopkinsschools.jarvis.slackapi.Group
import java.time.Instant

class GroupClosedEvent(group: Group, ts: Instant) : GroupEvent {
    override val name = "group_close";
    override val group = group;
    override val ts = ts;
}