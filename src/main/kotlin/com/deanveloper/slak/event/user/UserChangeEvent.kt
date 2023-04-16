package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import java.time.LocalDateTime

class UserChangeEvent(user: User, ts: LocalDateTime) : UserEvent {
    override val type = "user_change"
    override val ts = ts
    override val user = user
}
