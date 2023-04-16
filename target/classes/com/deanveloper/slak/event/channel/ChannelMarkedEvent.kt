package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import java.time.LocalDateTime

class ChannelMarkedEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
    override val type = "channel_marked"
    override val channel = channel
    override val ts = ts
}
