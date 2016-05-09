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
    val type: String
    val owner: User
    var text: String
    val ts: LocalDateTime
    var starred: Boolean
    var reactions: List<Reaction>

    constructor(type: String,
                owner: User,
                message: String,
                ts: LocalDateTime,
                starred: Boolean = false,
                reactions: List<Reaction> = emptyList()) {
        this.type = type
        this.owner = owner
        this.text = message
        this.ts = ts
        this.starred = starred
        this.reactions = reactions
    }

    constructor(json: JsonObject) {
        this.type = (json["subtype"] ?: json["type"]).asString
        this.owner = User[json["user"].asString]
        this.text = json["text"].asString
        this.ts = json["ts"].asTimestamp
        this.starred = json["is_starred"]?.asBoolean ?: false

        this.reactions = json["reactions"]?.asJsonArray?.map { it.asJsonObject }
            ?.map {
                Reaction(it["name"].asString, it["users"].asJsonArray.map { User[it.asString] }.toTypedArray())
            } ?: emptyList()

    }

    data class Reaction(val name: String, var users: Array<User>)
}
