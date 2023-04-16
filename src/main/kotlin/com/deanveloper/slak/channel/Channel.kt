package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.asUserList
import com.deanveloper.slak.nullSafe
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
        creator: User,
        created: LocalDateTime,
        archived: Boolean,
        general: Boolean,
        members: List<User>,
        topic: OwnedString.Topic?,
        purpose: OwnedString.Purpose?
) : BaseChannel<Channel>(id, name, creator, created, archived, general, members, topic, purpose, ChannelManager) {

    companion object ChannelManager : BaseChannel.ChannelCompanion<Channel>("channels") {
        override fun fromJson(json: JsonObject): Channel {
            if (!(json["is_channel"]?.asBoolean ?: false)) {
                throw IllegalArgumentException("json does not represent a channel!")
            }

            return Channel(json["id"].asString, json["name"].asString,
                    User[json["creator"].asString], json["created"].asTimestamp, json["is_archived"].asBoolean,
                    json["is_general"].asBoolean, json["members"].nullSafe?.asUserList ?: emptyList(),
                    if (json["topic"].nullSafe == null) OwnedString.Topic(json["topic"].asJsonObject) else null,
                    if (json["purpose"].nullSafe == null) OwnedString.Purpose(json["purpose"].asJsonObject) else null)
        }
    }
}
