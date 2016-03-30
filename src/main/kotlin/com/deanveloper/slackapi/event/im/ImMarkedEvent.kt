package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.slackapi.channel.ImChat
import java.time.Instant

class ImMarkedEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_marked";
    override val im = im;
    override val ts = ts;
}