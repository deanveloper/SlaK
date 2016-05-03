package com.deanveloper.slak.message

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a simple message.
 *
 * @author Dean B
 */
final class SimpleMessage : Message<String> {
	override val type = "message"
	override val owner: User
	override var message: String
	override val ts: LocalDateTime
	override var starred: Boolean
	override var reactions: Array<Reaction>

	constructor(owner: User,
	            message: String,
	            ts: LocalDateTime,
	            starred: Boolean = false,
	            reactions: Array<Reaction> = emptyArray()) {
		this.owner = owner
		this.message = message
		this.ts = ts
		this.starred = starred
		this.reactions = reactions
	}

	constructor(json: JsonObject) {
		if (!(json["type"].isJsonPrimitive && json["type"].asJsonPrimitive.isString)) {
			throw IllegalArgumentException("JsonElement 'type' is not a string!")
		}
		if (json["type"].asString == "message") {
			this.starred = json["is_starred"]?.asBoolean ?: false
			this.reactions = json["reactions"]?.asJsonArray?.map { it.asJsonObject }
					?.map {
						Reaction(it["name"].asString,
								it["users"].asJsonArray.map { User[it.asString] }.toTypedArray()
						)
					}
					?.toTypedArray()
					?: emptyArray()

			this.owner = User[json["user"].asString]
			this.message = json["text"].asString
			this.ts = json["ts"].asTimestamp
		} else {
			throw IllegalArgumentException("JsonElement 'type' is not 'message', " +
					"instead it is ${json["type"].asString}")
		}
	}
}