package org.hopkinsschools.jarvis.slackapi.event

import org.hopkinsschools.jarvis.slackapi.Group
import java.time.Instant

sealed class GroupEvent(val group: Group, name: String, ts: Instant) : Event {
    override val name = name;
    override val ts = ts;
    class GroupJoinedEvent(group: Group, ts: Instant) : GroupEvent(group, "group_joined", ts);
    class GroupLeftEvent(group: Group, ts: Instant) : GroupEvent(group, "group_left", ts);
    class GroupOpenedEvent(group: Group, ts: Instant) : GroupEvent(group, "group_open", ts);
    class GroupClosedEvent(group: Group, ts: Instant) : GroupEvent(group, "group_close", ts);
    class GroupArchiveEvent(group: Group, ts: Instant) : GroupEvent(group, "group_archive", ts);
    class GroupMarkedEvent(group: Group, ts: Instant) : GroupEvent(group, "group_marked", ts);
    class GroupHistoryChangeEvent(group: Group, ts: Instant) : GroupEvent(group, "group_history_changed", ts);
}