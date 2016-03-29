package org.hopkinsschools.jarvis.slackapi.event.im

import org.hopkinsschools.jarvis.slackapi.channel.ImChat
import java.time.Instant

class ImMarkedEvent(im: ImChat, ts: Instant) : ImEvent {
    override val name = "im_marked";
    override val im = im;
    override val ts = ts;
}