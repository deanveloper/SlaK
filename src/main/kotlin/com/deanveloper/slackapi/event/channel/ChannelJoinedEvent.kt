package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.LocalDateTime

class ChannelJoinedEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
	override val name = "channel_joined";
	override val channel = channel;
	override val ts = ts;
}