package com.deanveloper.slak.event.user

import com.deanveloper.slak.message.Message

class MessageEvent(msg: Message) : UserEvent {
    override val type = "message"
    override val ts = msg.ts
    override val user = msg.owner
}
