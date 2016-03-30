package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.channel.ImChat
import com.deanveloper.slackapi.event.Event

interface ImEvent : Event {
    val im: ImChat;

}