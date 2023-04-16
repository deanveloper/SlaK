package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class UserTypingEvent(user: User, ts: LocalDateTime) : UserEvent {
    override val type = "user_typing"
    override val ts = ts
    override val user = user
}
