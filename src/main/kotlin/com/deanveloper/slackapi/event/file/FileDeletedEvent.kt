package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.message.SlackFile
import java.time.LocalDateTime

class FileDeletedEvent(file: SlackFile, ts: LocalDateTime) : FileEvent {
	override val name = "file_deleted";
	override val file = file;
	override val ts = ts;
}