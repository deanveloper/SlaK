package org.hopkinsschools.jarvis.slackapi.event.im

import org.hopkinsschools.jarvis.slackapi.ImChat
import java.time.Instant

class ImCloseEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_close";
    override val im = im;
    override val ts = ts;
}