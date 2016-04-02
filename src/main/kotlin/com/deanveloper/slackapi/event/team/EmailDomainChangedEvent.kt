package com.deanveloper.slackapi.event.team

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class EmailDomainChangedEvent(ts: LocalDateTime) : Event {
	override val name = "email_domain_changed event";
	override val ts = ts;
}