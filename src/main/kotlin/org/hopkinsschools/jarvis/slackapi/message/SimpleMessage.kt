package org.hopkinsschools.jarvis.slackapi.message

import com.google.gson.JsonObject
import org.hopkinsschools.jarvis.slackapi.User
import java.time.LocalDateTime

/**
 * Represents a simple message
 *
 * @author Dean Bassett
 */
final data class SimpleMessage private constructor(override val owner: User,
                                                   override val message: String,
                                                   override val ts: LocalDateTime,
                                                   override val starred: Boolean = false,
                                                   override val reactions: Array<Message.Reaction> = emptyArray()
) : Message<String> {
    companion object {
        fun from(json: JsonObject) {
            if (!(json["type"].isJsonPrimitive && json["type"].asJsonPrimitive.isString)) {
                throw IllegalArgumentException("JsonElement 'type' is not a string!");
            }
            if (json["type"].asString == "message") {

            } else {
                throw IllegalArgumentException("JsonElement 'type' is not 'message', " +
                        "instead it is ${json["type"].asString}");
            }
        }
    }

    override val type = "message";
}