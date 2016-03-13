package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import java.time.Instant

class TeamPlanChangeEvent(ts: Instant) : Event {
    override val name = "team_plan_change";
    override val ts = ts;
}