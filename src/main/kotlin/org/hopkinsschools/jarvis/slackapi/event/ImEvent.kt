package org.hopkinsschools.jarvis.slackapi.event

import org.hopkinsschools.jarvis.slackapi.ImChat
import java.time.Instant

sealed class ImEvent(val im: ImChat, name: String, ts: Instant) : Event {
    override val name = name;
    override val ts = ts;

    class ImCreatedEvent(im: ImChat, ts: Instant) : ImEvent(im, "im_created", ts);
    class ImOpenEvent(im: ImChat, ts: Instant) : ImEvent(im, "im_open", ts);
    class ImCloseEvent(im: ImChat, ts: Instant) : ImEvent(im, "im_close", ts);
    class ImMarkedEvent(im: ImChat, ts: Instant) : ImEvent(im, "im_marked", ts);
    class ImHistoryChangedEvent(im: ImChat, ts: Instant) : ImEvent(im, "im_history_changed", ts);
}