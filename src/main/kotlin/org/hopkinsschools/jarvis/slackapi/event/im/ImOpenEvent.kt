package org.hopkinsschools.jarvis.slackapi.event.im

import org.hopkinsschools.jarvis.slackapi.ImChat
import java.time.Instant

class ImOpenEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_open";
    override val im = im;
    override val ts = ts;
}