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
class Message {
    val owner: User
    var message: String
    val ts: LocalDateTime
    var starred: Boolean
    var reactions: List<Reaction>

    constructor(owner: User,
                message: String,
                ts: LocalDateTime,
                starred: Boolean = false,
                reactions: List<Reaction> = emptyList()) {
        this.owner = owner
        this.message = message
        this.ts = ts
        this.starred = starred
        this.reactions = reactions
    }

    constructor(json: JsonObject) {
        this.starred = json["is_starred"]?.asBoolean ?: false
        this.reactions = json["reactions"]?.asJsonArray?.map { it.asJsonObject }
            ?.map {
                Reaction(it["name"].asString,
                    it["users"].asJsonArray.map { User[it.asString] }.toTypedArray()
                )
            } ?: emptyList()

        this.owner = User[json["user"].asString]
        this.message = json["text"].asString
        this.ts = json["ts"].asTimestamp
    }

    data class Reaction(val name: String, var users: Array<User>)
}
