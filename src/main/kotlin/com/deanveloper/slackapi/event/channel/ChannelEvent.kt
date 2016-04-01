package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.channel.Channel
import com.deanveloper.slackapi.event.Event

interface ChannelEvent : Event {
	val channel: Channel;
}