package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import java.time.LocalDateTime

class ChannelArchiveEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
    override val type = "channel_archive"
    override val channel = channel
    override val ts = ts
}
