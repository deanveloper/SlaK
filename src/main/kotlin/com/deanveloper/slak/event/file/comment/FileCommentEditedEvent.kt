package com.deanveloper.slak.event.file.comment

import com.deanveloper.slak.event.file.FileEvent
import com.deanveloper.slak.message.Comment
import com.deanveloper.slak.message.SlaKFile
import java.time.LocalDateTime

class FileCommentEditedEvent(comment: Comment, file: SlaKFile, ts: LocalDateTime) : FileEvent.FileCommentEvent {
    override val type = "file_comment_edited"
    override val ts = ts
    override val file = file
    override val comment = comment
    override val user = comment.user
}
