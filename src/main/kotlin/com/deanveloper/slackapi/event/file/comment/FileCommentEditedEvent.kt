package com.deanveloper.slackapi.event.file.comment

import com.deanveloper.slackapi.event.file.FileEvent
import com.deanveloper.slackapi.message.Comment
import com.deanveloper.slackapi.message.SlackFile
import java.time.LocalDateTime

class FileCommentEditedEvent(comment: Comment, file: SlackFile, ts: LocalDateTime) : FileEvent.FileCommentEvent {
	override val name = "file_comment_edited";
	override val ts = ts;
	override val file = file;
	override val comment = comment;
	override val user = comment.user;
}