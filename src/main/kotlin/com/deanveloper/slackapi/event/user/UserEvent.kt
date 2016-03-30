package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.User
import com.deanveloper.slackapi.event.Event

interface UserEvent : Event {
    val user: User;
}