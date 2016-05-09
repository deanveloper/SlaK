package com.deanveloper.slak.event.im

import com.deanveloper.slak.im.ImChat
import com.deanveloper.slak.event.Event

interface ImEvent : Event {
    val im: ImChat

}
