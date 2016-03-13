package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.Comment
import org.hopkinsschools.jarvis.slackapi.message.SlackFile
import java.time.Instant

class FileCommentDeletedEvent(comment: Comment, file: SlackFile, ts: Instant) : FileEvent.FileCommentEvent {
    override val name = "file_comment_deleted";
    override val ts = ts;
    override val file = file;
    override val comment = comment;
    override val user = comment.user
}