package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class PresenceChangedEvent(user: User, ts: Instant) : UserEvent {
    override val name = "presence_changed";
    override val ts = ts;
    override val user = user;
}