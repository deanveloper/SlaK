package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import java.time.LocalDateTime

class ChannelCreatedEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
    override val name = "channel_created"
    override val channel = channel
    override val ts = ts
}
