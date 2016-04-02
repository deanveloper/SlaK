package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.Instant

class ChannelMarkedEvent(channel: Channel, ts: Instant) : ChannelEvent {;
	override val name = "channel_marked";
	override val channel = channel;
	override val ts = ts
}