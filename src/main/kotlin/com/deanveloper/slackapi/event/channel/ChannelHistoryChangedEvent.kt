package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.Instant

class ChannelHistoryChangedEvent(channel: Channel, ts: Instant) : ChannelEvent {
    override val name = "channel_history_changed";
    override val channel = channel;
    override val ts = ts;
}