package org.hopkinsschools.jarvis.slackapi.event

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

sealed class UserEvent(val user: User, name: String, ts: Instant) : Event {
    override val name = name;
    override val ts = ts;

    class UserTypingEvent(user: User, ts: Instant) : UserEvent(user, "user_typing", ts);
    class UserDndEvent(user: User, ts: Instant) : UserEvent(user, "dnd_updated_user", ts);
    class MessageEvent(user: User, ts: Instant) : UserEvent(user, "message", ts);
    class PinAddedEvent(user: User, ts: Instant) : UserEvent(user, "pin_added", ts);
    class PinRemovedEvent(user: User, ts: Instant) : UserEvent(user, "pin_removed", ts);
    class PresenceChangedEvent(user: User, ts: Instant) : UserEvent(user, "presence_changed", ts);
    class UserChangeEvent(user: User, ts: Instant) : UserEvent(user, "user_change", ts);
    class TeamJoinEvent(user: User, ts: Instant) : UserEvent(user, "team_join", ts);
    class StarAddedEvent(user: User, ts: Instant) : UserEvent(user, "star_added", ts);
    class StarRemovedEvent(user: User, ts: Instant) : UserEvent(user, "star_removed", ts);
    class ReactionAddedEvent(user: User, ts: Instant) : UserEvent(user, "reaction_added", ts);
    class ReactionRemovedEvent(user: User, ts: Instant) : UserEvent(user, "reaction_removed", ts);
}