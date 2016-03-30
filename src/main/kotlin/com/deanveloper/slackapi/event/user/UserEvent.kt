package com.deanveloper.slackapi.event.user

import com.deanveloper.slackapi.slackapi.User
import com.deanveloper.slackapi.slackapi.event.Event

interface UserEvent : Event {
    val user: User;
}