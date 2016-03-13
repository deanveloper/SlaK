package org.hopkinsschools.jarvis.slackapi.event.channel

import org.hopkinsschools.jarvis.slackapi.Channel
import java.time.Instant

class ChannelUnarchiveEvent(channel: Channel, ts: Instant) : ChannelEvent {
    override val name = "channel_unarchive";
    override val channel = channel;
    override val ts = ts;
}