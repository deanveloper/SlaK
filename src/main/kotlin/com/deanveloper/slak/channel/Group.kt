package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.asUserList
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a private channel
 */
class Group(
    id: String,
    name: String,
    created: LocalDateTime,
    archived: Boolean,
    general: Boolean,
    members: List<User>?,
    topic: OwnedString.Topic,
    purpose: OwnedString.Purpose
) : BaseChannel<Group>(id, name, created, archived, general, members, topic, purpose) {
    override val handler = GroupManager

    companion object GroupManager : BaseChannel.ChannelCompanion<Group>("groups") {
        override fun fromJson(json: JsonObject) = Group(json["id"].asString, json["name"].asString,
            json["created"].asTimestamp, json["is_archived"].asBoolean,
            json["is_general"].asBoolean, json["members"]?.asUserList,
            OwnedString.Topic(json["topic"].asJsonObject), OwnedString.Purpose(json["purpose"].asJsonObject))
    }
}
