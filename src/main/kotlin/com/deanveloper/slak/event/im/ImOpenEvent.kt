package com.deanveloper.slak.event.im

import com.deanveloper.slak.im.ImChat
import java.time.LocalDateTime

class ImOpenEvent(im: ImChat, ts: LocalDateTime) : ImEvent {
    override val type = "im_open"
    override val im = im
    override val ts = ts
}
