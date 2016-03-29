package org.hopkinsschools.jarvis.slackapi.event.channel

import org.hopkinsschools.jarvis.slackapi.channel.Channel
import java.time.Instant

class ChannelCreatedEvent(channel: Channel, ts: Instant) : ChannelEvent {
    override val name = "channel_created";
    override val channel = channel;
    override val ts = ts;
}