package com.deanveloper.slak.event.user

import com.deanveloper.slak.User
import com.deanveloper.slak.event.Event

interface UserEvent : Event {
    val user: User
}
