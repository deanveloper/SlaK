package com.deanveloper.slak.event.im

import com.deanveloper.slak.event.Event
import com.deanveloper.slak.im.ImChat

interface ImEvent : Event {
    val im: ImChat

}
