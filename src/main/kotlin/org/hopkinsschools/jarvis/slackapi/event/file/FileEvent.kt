package org.hopkinsschools.jarvis.slackapi.event.file

import org.hopkinsschools.jarvis.slackapi.Comment
import org.hopkinsschools.jarvis.slackapi.event.Event
import org.hopkinsschools.jarvis.slackapi.event.user.UserEvent
import org.hopkinsschools.jarvis.slackapi.message.SlackFile

interface FileEvent: Event {
    val file: SlackFile;

    interface FileCommentEvent : FileEvent, UserEvent {
        val comment: Comment;
    }

}