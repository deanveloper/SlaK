package com.deanveloper.slak.event.file

import com.deanveloper.slak.event.Event
import com.deanveloper.slak.event.user.UserEvent
import com.deanveloper.slak.message.Comment
import com.deanveloper.slak.message.SlackFile

interface FileEvent : Event {
	val file: SlackFile;

	interface FileCommentEvent : FileEvent, UserEvent {
		val comment: Comment;
	}

}