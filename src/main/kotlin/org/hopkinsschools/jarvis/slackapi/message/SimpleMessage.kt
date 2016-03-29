package org.hopkinsschools.jarvis.slackapi.message

import com.google.gson.JsonObject
import org.hopkinsschools.jarvis.slackapi.User
import java.time.LocalDateTime

/**
 * Represents a simple message
 *
 * @author Dean Bassett
 */
final class SimpleMessage private constructor(owner: User,
                                              message: String,
                                              ts: LocalDateTime,
                                              starred: Boolean = false,
                                              reactions: Array<Message.Reaction> = emptyArray()
) : Message<String>("message", owner, message, ts, starred, reactions) {
    companion object {
        fun from(json: JsonObject): SimpleMessage {
            if (!(json["type"].isJsonPrimitive && json["type"].asJsonPrimitive.isString)) {
                throw IllegalArgumentException("JsonElement 'type' is not a string!");
            }
            if (json["type"].asString == "message") {
                return TODO("Not implemented yet.");
            } else {
                throw IllegalArgumentException("JsonElement 'type' is not 'message', " +
                        "instead it is ${json["type"].asString}");
            }
        }
    }
}