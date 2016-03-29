package org.hopkinsschools.jarvis.slackapi.event.im

import org.hopkinsschools.jarvis.slackapi.channel.ImChat
import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

interface ImEvent : Event {
    val im: ImChat;

}