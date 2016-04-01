package com.deanveloper.slackapi.event

import java.time.Instant

interface Event {
	val name: String;
	val ts: Instant;
}