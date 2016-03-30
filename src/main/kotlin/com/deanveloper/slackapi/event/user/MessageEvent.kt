package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class MessageEvent(user: User, ts: Instant) : UserEvent {
    override val name = "message";
    override val ts = ts;
    override val user = user;
}