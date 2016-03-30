package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.slackapi.User
import java.time.Instant

class ReactionRemovedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "reaction_removed";
    override val ts = ts;
    override val user = user;
}