package com.deanveloper.slak.event.team

import com.deanveloper.slak.event.Event
import java.time.LocalDateTime

/**
 * @author Dean B
 */
class TeamProfileDeleteEvent(ts: LocalDateTime) : Event {
    override val type = "team_profile_delete"
    override val ts = ts
}
