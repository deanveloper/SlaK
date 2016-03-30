package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import java.time.Instant

class UserChangeEvent(user: User, ts: Instant) : UserEvent {
    override val name = "user_change";
    override val ts = ts;
    override val user = user;
}