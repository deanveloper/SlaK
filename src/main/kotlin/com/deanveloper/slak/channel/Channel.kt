package com.deanveloper.slak.channel

import com.deanveloper.slak.TOKEN
import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.message.Message
import com.deanveloper.slak.message.SimpleMessage
import com.deanveloper.slak.runMethod
import com.deanveloper.slak.util.Cacher
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*

/**
 * API for channels
 *
 * @author Dean B
 */
class Channel private constructor(override val id: String,
                                  override var name: String,
                                  override val created: LocalDateTime,
                                  override var archived: Boolean,
                                  override val general: Boolean,
                                  override val members: List<User>?,
                                  override val topic: OwnedString.Topic,
                                  override val purpose: OwnedString.Purpose
) : BaseChannel<Channel>() {

}

