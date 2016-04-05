package com.deanveloper.slak.event.file.comment

import com.deanveloper.slak.event.file.FileEvent
import com.deanveloper.slak.message.Comment
import com.deanveloper.slak.message.SlackFile
import java.time.LocalDateTime

class FileCommentAddedEvent(comment: Comment, file: SlackFile, ts: LocalDateTime) : FileEvent.FileCommentEvent {
	override val name = "file_comment_added";
	override val user = comment.user;
	override val ts = ts;
	override val file = file;
	override val comment = comment;
}