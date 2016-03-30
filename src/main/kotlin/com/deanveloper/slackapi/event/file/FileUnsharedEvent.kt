package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.message.SlackFile
import java.time.Instant

class FileUnsharedEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_unshared";
    override val file = file;
    override val ts = ts;
}