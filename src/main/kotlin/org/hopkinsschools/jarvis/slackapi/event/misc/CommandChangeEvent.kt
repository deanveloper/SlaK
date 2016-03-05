package org.hopkinsschools.jarvis.slackapi.event.misc

import org.hopkinsschools.jarvis.slackapi.event.Event
import org.hopkinsschools.jarvis.slackapi.event.EventHandler
import java.time.Instant

class CommandChangeEvent(ts: Instant) : Event("command_changed", ts)