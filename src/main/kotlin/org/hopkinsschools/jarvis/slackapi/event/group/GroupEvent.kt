package org.hopkinsschools.jarvis.slackapi.event.group

import org.hopkinsschools.jarvis.slackapi.Group
import org.hopkinsschools.jarvis.slackapi.event.Event

interface GroupEvent : Event {
    val group: Group;
}