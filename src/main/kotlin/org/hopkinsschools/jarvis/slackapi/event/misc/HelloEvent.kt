package org.hopkinsschools.jarvis.slackapi.event

import java.time.Instant

class HelloEvent(ts: Instant) : Event("hello", ts)