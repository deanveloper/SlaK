package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class TeamProfileChangeEvent(ts: LocalDateTime) : Event {
	override val name = "team_profile_change"
	override val ts = ts
}