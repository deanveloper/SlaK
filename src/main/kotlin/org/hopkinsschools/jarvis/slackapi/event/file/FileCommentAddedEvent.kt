package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.Comment
import org.hopkinsschools.jarvis.slackapi.message.SlackFile
import java.time.Instant

class FileCommentAddedEvent(comment: Comment, file: SlackFile, ts: Instant) : FileEvent.FileCommentEvent {
    override val name = "file_comment_added";
    override val user = comment.user;
    override val ts = ts;
    override val file = file;
    override val comment = comment;
}