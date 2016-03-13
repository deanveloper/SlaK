package org.hopkinsschools.jarvis.slackapi.event.channel

import org.hopkinsschools.jarvis.slackapi.Channel
import java.time.Instant

class ChannelLeftEvent(channel: Channel, ts: Instant) : ChannelEvent {
    override val name = "channel_left";
    override val channel = channel;
    override val ts = ts;
}