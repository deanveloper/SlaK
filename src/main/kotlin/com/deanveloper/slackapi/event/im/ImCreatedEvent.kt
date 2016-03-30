package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.channel.ImChat
import java.time.Instant

class ImCreatedEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_created";
    override val im = im;
    override val ts = ts;
}