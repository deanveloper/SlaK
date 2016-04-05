package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

/**
 * When the team's domain changes
 *
 * @author Dean B
 */
class TeamDomainChangeEvent(ts: LocalDateTime) : Event {
	override val name = "team_domain_change";
	override val ts = ts;
}
