package com.deanveloper.slackapi.event.team

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class TeamProfileChangeEvent(ts: LocalDateTime) : Event {
	override val name = "team_profile_change"
	override val ts = ts;
}