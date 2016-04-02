package com.deanveloper.slackapi.event.file.comment

import com.deanveloper.slackapi.Comment
import com.deanveloper.slackapi.event.file.FileEvent
import com.deanveloper.slackapi.message.SlackFile
import java.time.Instant

class FileCommentDeletedEvent(comment: Comment, file: SlackFile, ts: Instant) : FileEvent.FileCommentEvent {
	override val name = "file_comment_deleted";
	override val ts = ts;
	override val file = file;
	override val comment = comment;
	override val user = comment.user
}