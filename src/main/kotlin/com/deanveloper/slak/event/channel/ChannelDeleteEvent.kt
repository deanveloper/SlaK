package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import java.time.LocalDateTime

class ChannelDeleteEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
	override val name = "channel_deleted";
	override val channel = channel;
	override val ts = ts;
}