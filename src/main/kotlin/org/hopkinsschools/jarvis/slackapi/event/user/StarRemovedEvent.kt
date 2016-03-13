package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class StarRemovedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "star_removed";
    override val ts = ts;
    override val user = user;
}