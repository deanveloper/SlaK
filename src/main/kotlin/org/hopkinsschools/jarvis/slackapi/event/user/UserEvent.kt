package org.hopkinsschools.jarvis.slackapi.event.user

import org.hopkinsschools.jarvis.slackapi.User
import org.hopkinsschools.jarvis.slackapi.event.Event

interface UserEvent : Event {
    val user: User;
}