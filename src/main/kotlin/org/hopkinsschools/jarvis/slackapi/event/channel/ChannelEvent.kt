package org.hopkinsschools.jarvis.slackapi.event.channel

import org.hopkinsschools.jarvis.slackapi.Channel
import org.hopkinsschools.jarvis.slackapi.event.Event

interface ChannelEvent : Event {
    val channel: Channel;
}