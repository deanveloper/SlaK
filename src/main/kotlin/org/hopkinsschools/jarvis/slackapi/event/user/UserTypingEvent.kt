package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class UserTypingEvent(user: User, ts: Instant) : UserEvent {
    override val name = "user_typing";
    override val ts = ts;
    override val user = user;
}