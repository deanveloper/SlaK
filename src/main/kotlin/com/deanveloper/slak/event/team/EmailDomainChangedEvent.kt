package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class EmailDomainChangedEvent(ts: LocalDateTime) : Event {
	override val name = "email_domain_changed"
	override val ts = ts
}