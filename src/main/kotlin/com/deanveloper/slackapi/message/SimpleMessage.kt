package com.deanveloper.slackapi.message

import com.google.gson.JsonObject
import com.deanveloper.slackapi.User
import com.deanveloper.slackapi.getAsTimestamp
import java.time.LocalDateTime

/**
 * Represents a simple message
 *
 * @author Dean B
 */
final class SimpleMessage private constructor(owner: User,
                                              message: String,
                                              ts: LocalDateTime,
                                              starred: Boolean,
                                              reactions: Array<Reaction>
) : Message<String>("message", owner, message, ts, starred, reactions) {
	companion object {
		fun from(json: JsonObject): SimpleMessage {
			if (!(json["type"].isJsonPrimitive && json["type"].asJsonPrimitive.isString)) {
				throw IllegalArgumentException("JsonElement 'type' is not a string!");
			}
			if (json["type"].asString == "message") {
				val starred = json["is_starred"]?.asBoolean ?: false;
				val reactions = json["reactions"]?.asJsonArray?.map { it.asJsonObject }
						?.map {
							Reaction(it["name"].asString,
									it["users"].asJsonArray.map { User[it.asString]!! }.toTypedArray()
							)
						}
						?.toTypedArray()
						?: emptyArray();

				return SimpleMessage(User[json["user"].asString]!!, json["text"].asString, json["ts"].getAsTimestamp(),
						starred, reactions);
			} else {
				throw IllegalArgumentException("JsonElement 'type' is not 'message', " +
						"instead it is ${json["type"].asString}");
			}
		}
	}
}