package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.Comment
import com.deanveloper.slackapi.event.Event
import com.deanveloper.slackapi.event.user.UserEvent
import com.deanveloper.slackapi.message.SlackFile

interface FileEvent: Event {
    val file: SlackFile;

    interface FileCommentEvent : FileEvent, UserEvent {
        val comment: Comment;
    }

}