package org.hopkinsschools.jarvis.slackapi.event.channel

import org.hopkinsschools.jarvis.slackapi.Channel
import java.time.Instant

class ChannelMarkedEvent(channel: Channel, ts: Instant) : ChannelEvent {;
    override val name = "channel_marked";
    override val channel = channel;
    override val ts = ts
}