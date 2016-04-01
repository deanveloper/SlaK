package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.Instant

class ChannelLeftEvent(channel: Channel, ts: Instant) : ChannelEvent {
	override val name = "channel_left";
	override val channel = channel;
	override val ts = ts;
}