package com.deanveloper.slak.event.im

import com.deanveloper.slak.channel.ImChat
import java.time.LocalDateTime

class ImMarkedEvent(im: ImChat, ts: LocalDateTime) : ImEvent {
	override val name = "im_marked";
	override val im = im;
	override val ts = ts;
}