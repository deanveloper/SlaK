package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class PresenceChangedEvent(user: User, ts: LocalDateTime) : UserEvent {
    override val type = "presence_changed"
    override val ts = ts
    override val user = user
}
