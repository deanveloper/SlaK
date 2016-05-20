package com.deanveloper.slak.im

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.asUserList
import com.deanveloper.slak.channel.BaseChannel
import com.deanveloper.slak.channel.OwnedString
import com.deanveloper.slak.nullSafe
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a direct message with multiple users
 *
 * @author Dean B
 */
class MpimChat private constructor(
        id: String,
        name: String,
        creator: User,
        created: LocalDateTime,
        archived: Boolean,
        members: List<User>,
        topic: OwnedString.Topic?,
        purpose: OwnedString.Purpose?
) : BaseChannel<MpimChat>(id, name, creator, created, archived, false, members, topic, purpose, MpimManager) {
    companion object MpimManager : ChannelCompanion<MpimChat>("mpim") {
        override fun fromJson(json: JsonObject): MpimChat {
            if(!(json["is_mpim"]?.asBoolean ?: false)) {
                throw IllegalArgumentException("json does not represent an mpim")
            }

            return MpimChat(json["id"].asString, json["name"].asString,
            User[json["creator"].asString], json["created"].asTimestamp, json["is_archived"].asBoolean,
            json["members"].nullSafe?.asUserList ?: emptyList(),
            if (json["topic"].nullSafe == null) OwnedString.Topic(json["topic"].asJsonObject) else null,
            if (json["purpose"].nullSafe == null) OwnedString.Purpose(json["purpose"].asJsonObject) else null)
        }
    }
}

