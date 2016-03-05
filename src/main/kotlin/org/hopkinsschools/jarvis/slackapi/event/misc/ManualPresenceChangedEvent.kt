package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import org.hopkinsschools.jarvis.slackapi.event.EventHandler
import java.time.Instant

class ManualPresenceChangedEvent(ts: Instant) : Event("manual_presence_changed", ts)