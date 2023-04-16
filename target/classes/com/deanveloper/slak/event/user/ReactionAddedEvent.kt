package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class ReactionAddedEvent(user: User, ts: LocalDateTime) : UserEvent {
    override val type = "reaction_added"
    override val ts = ts
    override val user = user
}
