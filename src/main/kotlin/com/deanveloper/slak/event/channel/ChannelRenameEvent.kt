package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import java.time.LocalDateTime

class ChannelRenameEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
	override val name = "channel_rename";
	override val channel = channel;
	override val ts = ts;
}