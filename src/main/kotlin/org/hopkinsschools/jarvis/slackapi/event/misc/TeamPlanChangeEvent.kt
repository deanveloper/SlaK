package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import org.hopkinsschools.jarvis.slackapi.event.EventHandler
import java.time.Instant

class TeamPlanChangeEvent(ts: Instant) : Event("team_plan_change", ts)