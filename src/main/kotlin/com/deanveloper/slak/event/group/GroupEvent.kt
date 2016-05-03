package com.deanveloper.slak.event.group

import com.deanveloper.slak.channel.Group
import com.deanveloper.slak.event.Event

interface GroupEvent : Event {
	val group: Group
}