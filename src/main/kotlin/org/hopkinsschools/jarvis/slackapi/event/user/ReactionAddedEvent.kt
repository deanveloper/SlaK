package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class ReactionAddedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "reaction_added";
    override val ts = ts;
    override val user = user;
}