package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.slackapi.Comment
import com.deanveloper.slackapi.slackapi.event.Event
import com.deanveloper.slackapi.slackapi.event.user.UserEvent
import com.deanveloper.slackapi.slackapi.message.SlackFile

interface FileEvent: Event {
    val file: SlackFile;

    interface FileCommentEvent : FileEvent, UserEvent {
        val comment: Comment;
    }

}