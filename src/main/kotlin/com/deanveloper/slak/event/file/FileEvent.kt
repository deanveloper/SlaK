package com.deanveloper.slak.event.file

import com.deanveloper.slak.event.Event
import com.deanveloper.slak.event.user.UserEvent
import com.deanveloper.slak.message.Comment
import com.deanveloper.slak.message.SlaKFile

interface FileEvent : Event {
    val file: SlaKFile

    interface FileCommentEvent : FileEvent, UserEvent {
        val comment: Comment
    }

}
