package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class TeamJoinEvent(user: User, ts: Instant) : UserEvent {
    override val name = "team_join";
    override val ts = ts;
    override val user = user;
}