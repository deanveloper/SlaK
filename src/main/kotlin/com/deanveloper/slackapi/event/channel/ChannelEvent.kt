package com.deanveloper.slackapi.event.channel

import com.deanveloper.slackapi.slackapi.channel.Channel
import com.deanveloper.slackapi.slackapi.event.Event

interface ChannelEvent : Event {
    val channel: Channel;
}