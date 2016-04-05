package com.deanveloper.slak.event.im

import com.deanveloper.slak.channel.ImChat
import java.time.LocalDateTime

class ImOpenEvent(im: ImChat, ts: LocalDateTime) : ImEvent {
	override val name = "im_open";
	override val im = im;
	override val ts = ts;
}