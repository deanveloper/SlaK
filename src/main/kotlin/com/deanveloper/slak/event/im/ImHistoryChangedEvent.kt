package com.deanveloper.slak.event.im

import com.deanveloper.slak.im.ImChat
import java.time.LocalDateTime

class ImHistoryChangedEvent(im: ImChat, ts: LocalDateTime) : ImEvent {
	override val name = "im_history_changed"
	override val im = im
	override val ts = ts
}