package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class MessageEvent(user: User, ts: Instant) : UserEvent {
    override val name = "message";
    override val ts = ts;
    override val user = user;
}