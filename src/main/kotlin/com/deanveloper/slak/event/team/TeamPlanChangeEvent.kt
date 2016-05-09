package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

class TeamPlanChangeEvent(ts: LocalDateTime) : Event {
    override val name = "team_plan_change"
    override val ts = ts
}
