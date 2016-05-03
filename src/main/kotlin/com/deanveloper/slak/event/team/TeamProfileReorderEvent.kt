package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class TeamProfileReorderEvent(ts: LocalDateTime) : Event {
	override val name = "team_profile_reorder"
	override val ts = ts
}