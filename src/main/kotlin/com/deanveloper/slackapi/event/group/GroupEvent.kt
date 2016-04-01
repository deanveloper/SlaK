package com.deanveloper.slackapi.event.group

import com.deanveloper.slackapi.channel.Group
import com.deanveloper.slackapi.event.Event

interface GroupEvent : Event {
	val group: Group;
}