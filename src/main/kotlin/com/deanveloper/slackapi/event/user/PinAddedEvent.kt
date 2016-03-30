package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class PinAddedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "pin_added";
    override val ts = ts;
    override val user = user;
}