package com.deanveloper.slak.event

import java.time.LocalDateTime

interface Event {
	val name: String;
	val ts: LocalDateTime;
}