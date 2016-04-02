package com.deanveloper.slackapi.event.im

import com.deanveloper.slackapi.channel.ImChat
import java.time.LocalDateTime

class ImCreatedEvent(im: ImChat, ts: LocalDateTime) : ImEvent {
	override val name = "im_created";
	override val im = im;
	override val ts = ts;
}