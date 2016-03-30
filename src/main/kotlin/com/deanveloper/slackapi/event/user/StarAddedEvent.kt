package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.slackapi.User
import java.time.Instant

class StarAddedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "star_added";
    override val ts = ts;
    override val user = user;
}