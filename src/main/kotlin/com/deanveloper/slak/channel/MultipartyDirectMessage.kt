package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.asUserList
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a direct message with multiple users
 *
 * @author Dean B
 */
class MultipartyDirectMessage private constructor(
		id: String,
		name: String,
		created: LocalDateTime,
		archived: Boolean,
		general: Boolean,
		members: List<User>?,
		topic: OwnedString.Topic,
		purpose: OwnedString.Purpose
) : BaseChannel<MultipartyDirectMessage>(id, name, created, archived, general, members, topic, purpose, "channels") {
	override val handler = object : BaseChannel<MultipartyDirectMessage>.ChannelCompanion() {
		override fun fromJson(json: JsonObject) = MultipartyDirectMessage(json["id"].asString, json["name"].asString,
				json["created"].asTimestamp, json["is_archived"].asBoolean,
				json["is_general"].asBoolean, json["members"]?.asUserList,
				OwnedString.Topic(json["topic"].asJsonObject), OwnedString.Purpose(json["purpose"].asJsonObject))
	}
}

