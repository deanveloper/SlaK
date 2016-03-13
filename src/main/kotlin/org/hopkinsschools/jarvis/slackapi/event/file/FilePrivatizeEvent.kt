package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.message.SlackFile
import java.time.Instant

class FilePrivatizeEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_private";
    override val file = file;
    override val ts = ts;
}