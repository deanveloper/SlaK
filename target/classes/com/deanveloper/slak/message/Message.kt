package com.deanveloper.slak.message

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*

/**
 * Represents a simple message.
 *
 * @author Dean B
 */
data class Message(
        val type: String,
        val owner: User,
        val message: String,
        val ts: LocalDateTime,
        val starred: Boolean = false,
        val reactions: List<Reaction> = emptyList()
) {

    constructor(json: JsonObject) : this(
            (json["subtype"] ?: json["type"]).asString,
            User[json["user"].asString],
            json["text"].asString,
            json["ts"].asTimestamp,
            json["is_starred"]?.asBoolean ?: false,
            json["reactions"]?.asJsonArray?.map { it.asJsonObject }
                    ?.map {
                        Reaction(it["name"].asString, it["users"].asJsonArray.map { User[it.asString] }.toTypedArray())
                    } ?: emptyList()
    )

    data class Reaction(val name: String, var users: Array<User>) {
        override fun equals(other: Any?): Boolean {
            if (other !is Reaction) return false //smart cast! :)
            return name == other.name && Arrays.equals(users, other.users)
        }

        override fun hashCode() = name.hashCode() xor users.size
        override fun toString() = "Reaction[$name|${users.joinToString(",")}"
    }
}
