package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.message.SlackFile
import java.time.Instant

class FileSharedEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_shared";
    override val file = file;
    override val ts = ts;
}