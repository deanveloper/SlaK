package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import java.time.LocalDateTime

class ChannelArchiveEvent(channel: Channel, ts: LocalDateTime) : ChannelEvent {
	override val name = "channel_archive";
	override val channel = channel;
	override val ts = ts;
}