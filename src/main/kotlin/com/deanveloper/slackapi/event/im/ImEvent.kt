package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.slackapi.channel.ImChat
import com.deanveloper.slackapi.slackapi.event.Event
import java.time.Instant

interface ImEvent : Event {
    val im: ImChat;

}