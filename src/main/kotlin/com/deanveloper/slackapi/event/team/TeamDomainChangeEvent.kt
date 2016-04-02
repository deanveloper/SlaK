package com.deanveloper.slackapi.event.team

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

/**
 * When the team's domain changes
 *
 * @author Dean B
 */
class TeamDomainChangeEvent(ts: LocalDateTime) : Event {
	override val name = "team_domain_change event";
	override val ts = ts;
}
