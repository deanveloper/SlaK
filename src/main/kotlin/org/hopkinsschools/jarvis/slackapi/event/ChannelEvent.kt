package org.hopkinsschools.jarvis.slackapi.event

import org.hopkinsschools.jarvis.slackapi.Channel
import java.time.Instant

sealed class ChannelEvent(val channel: Channel, name: String, ts: Instant) : Event {
    override val name = name;
    override val ts = ts;

    class ChannelMarkedEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_marked", ts);
    class ChannelCreatedEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_created", ts);
    class ChannelJoinedEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_joined", ts);
    class ChannelLeftEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_left", ts);
    class ChannelDeleteEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_deleted", ts);
    class ChannelRenameEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_rename", ts);
    class ChannelArchiveEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_archive", ts);
    class ChannelUnarchiveEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_unarchive", ts);
    class ChannelHistoryChangedEvent(channel: Channel, ts: Instant) : ChannelEvent(channel, "channel_history_changed", ts);
}