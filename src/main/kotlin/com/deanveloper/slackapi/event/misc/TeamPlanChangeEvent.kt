package com.deanveloper.slackapi.event.misc

import com.deanveloper.slackapi.event.Event
import java.time.Instant

class TeamPlanChangeEvent(ts: Instant) : Event {
	override val name = "team_plan_change";
	override val ts = ts;
}