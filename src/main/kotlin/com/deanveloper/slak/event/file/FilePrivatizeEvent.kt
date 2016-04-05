package com.deanveloper.slak.event.file

import com.deanveloper.slak.message.SlackFile
import java.time.LocalDateTime

class FilePrivatizeEvent(file: SlackFile, ts: LocalDateTime) : FileEvent {
	override val name = "file_private";
	override val file = file;
	override val ts = ts;
}