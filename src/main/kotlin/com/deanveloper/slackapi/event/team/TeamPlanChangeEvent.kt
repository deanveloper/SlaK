package com.deanveloper.slackapi.event.team

import com.deanveloper.slackapi.event.Event
import java.time.LocalDateTime

class TeamPlanChangeEvent(ts: LocalDateTime) : Event {
	override val name = "team_plan_change";
	override val ts = ts;
}