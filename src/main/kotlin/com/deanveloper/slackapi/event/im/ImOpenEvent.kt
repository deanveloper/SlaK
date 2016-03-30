package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.channel.ImChat
import java.time.Instant

class ImOpenEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_open";
    override val im = im;
    override val ts = ts;
}