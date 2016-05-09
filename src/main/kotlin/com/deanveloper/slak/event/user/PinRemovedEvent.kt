package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class PinRemovedEvent(user: User, ts: LocalDateTime) : UserEvent {
    override val name = "pin_removed"
    override val ts = ts
    override val user = user
}
