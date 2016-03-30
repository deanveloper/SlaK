package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.channel.ImChat
import java.time.Instant

class ImCloseEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_close";
    override val im = im;
    override val ts = ts;
}