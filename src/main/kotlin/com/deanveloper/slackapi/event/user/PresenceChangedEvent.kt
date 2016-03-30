package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.slackapi.User
import java.time.Instant

class PresenceChangedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "presence_changed";
    override val ts = ts;
    override val user = user;
}