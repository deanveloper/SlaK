package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.message.SlackFile
import java.time.LocalDateTime

class FileCreatedEvent(file: SlackFile, ts: LocalDateTime) : FileEvent {
	override val name = "file_created";
	override val file = file;
	override val ts = ts;
}