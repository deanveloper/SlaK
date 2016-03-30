package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.Instant

class ChannelDeleteEvent(channel: Channel, ts: Instant) : ChannelEvent {
    override val name = "channel_deleted";
    override val channel = channel;
    override val ts = ts;
}