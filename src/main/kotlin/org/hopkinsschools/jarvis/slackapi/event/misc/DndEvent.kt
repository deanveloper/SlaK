package org.hopkinsschools.jarvis.slackapi.event

import java.time.Instant

class DndEvent(ts: Instant) : Event("dnd_updated", ts)