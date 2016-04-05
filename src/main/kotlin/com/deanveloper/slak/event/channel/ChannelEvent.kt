package com.deanveloper.slak.event.channel

import com.deanveloper.slak.channel.Channel
import com.deanveloper.slak.event.Event

interface ChannelEvent : Event {
	val channel: Channel;
}