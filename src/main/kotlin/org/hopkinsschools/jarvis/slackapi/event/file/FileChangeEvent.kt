package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.message.SlackFile
import java.time.Instant

class FileChangeEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_change";
    override val file = file;
    override val ts = ts;
}