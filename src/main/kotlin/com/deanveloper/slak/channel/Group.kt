package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.asUserList
import com.deanveloper.slak.nullSafe
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a private channel
 */
class Group(
        id: String,
        name: String,
        creator: User,
        created: LocalDateTime,
        archived: Boolean,
        general: Boolean,
        members: List<User>,
        topic: OwnedString.Topic?,
        purpose: OwnedString.Purpose?
) : BaseChannel<Group>(id, name, creator, created, archived, general, members, topic, purpose, GroupManager) {
    companion object GroupManager : BaseChannel.ChannelCompanion<Group>("groups") {
        override fun fromJson(json: JsonObject) = Group(json["id"].asString, json["name"].asString,
                User[json["creator"].asString], json["created"].asTimestamp, json["is_archived"]?.asBoolean ?: false,
                false, json["members"]?.asUserList ?: emptyList(),
                if (json["topic"].nullSafe == null) OwnedString.Topic(json["topic"].asJsonObject) else null,
                if (json["purpose"].nullSafe == null) OwnedString.Purpose(json["purpose"].asJsonObject) else null)
    }
}
