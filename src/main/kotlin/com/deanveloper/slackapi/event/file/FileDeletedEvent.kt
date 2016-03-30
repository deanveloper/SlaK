package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.message.SlackFile
import java.time.Instant

class FileDeletedEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_deleted";
    override val file = file;
    override val ts = ts;
}