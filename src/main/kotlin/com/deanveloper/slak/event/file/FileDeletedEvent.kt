package com.deanveloper.slak.event.file

import com.deanveloper.slak.message.SlackFile
import java.time.LocalDateTime

class FileDeletedEvent(file: SlackFile, ts: LocalDateTime) : FileEvent {
	override val name = "file_deleted";
	override val file = file;
	override val ts = ts;
}