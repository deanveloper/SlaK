package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.slackapi.message.SlackFile
import java.time.Instant

class FileChangeEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_change";
    override val file = file;
    override val ts = ts;
}