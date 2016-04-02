package com.deanveloper.slackapi.event

import java.time.LocalDateTime

interface Event {
	val name: String;
	val ts: LocalDateTime;
}