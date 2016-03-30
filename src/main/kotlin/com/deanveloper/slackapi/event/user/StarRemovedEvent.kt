package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class StarRemovedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "star_removed";
    override val ts = ts;
    override val user = user;
}