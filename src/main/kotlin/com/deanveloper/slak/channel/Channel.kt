package com.deanveloper.slak.channel

import com.deanveloper.slak.*
import com.deanveloper.slak.util.ErrorHandler
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a channel
 *
 * @author Dean B
 */
class Channel private constructor(
    id: String,
    name: String,
    created: LocalDateTime,
    archived: Boolean,
    general: Boolean,
    members: List<User>?,
    topic: OwnedString.Topic,
    purpose: OwnedString.Purpose
) : BaseChannel<Channel>(id, name, created, archived, general, members, topic, purpose) {
    override val handler = ChannelManager

    companion object ChannelManager : BaseChannel.ChannelCompanion<Channel>("channels") {
        override fun fromJson(json: JsonObject) = Channel(json["id"].asString, json["name"].asString,
            json["created"].asTimestamp, json["is_archived"].asBoolean,
            json["is_general"].asBoolean, json["members"]?.asUserList,
            OwnedString.Topic(json["topic"].asJsonObject), OwnedString.Purpose(json["purpose"].asJsonObject))
    }
}
