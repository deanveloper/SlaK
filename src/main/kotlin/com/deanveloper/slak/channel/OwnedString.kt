package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a string that is owned by a [User] (aka a channel purpose or topic).
 *
 * @property[owner]     the owner of the string
 * @property[string]    the string that is being owned
 * @property[ts]        the time the owned string was created
 */
sealed class OwnedString(open val owner: User, protected open val string: String, open val ts: LocalDateTime) {

    /**
     * Represents the purpose of a channel.
     *
     * @property[owner]     the owner of the string
     * @property[purpose]   the purpose of the channel
     * @property[ts]        the time the purpose was set
     */
    class Purpose(
            override val owner: User,
            val purpose: String,
            override val ts: LocalDateTime
    ) : OwnedString(owner, purpose, ts) {
        constructor(json: JsonObject) : this(
                User[json["owner"].asString],
                json["value"].asString,
                json["last_set"].asTimestamp
        )
    }

    /**
     * Represents the topic of a channel.
     *
     * @property[owner] the owner of the string
     * @property[topic] the topic of the channel
     * @property[ts]    the time the purpose was set
     */
    class Topic(
            override val owner: User,
            val topic: String,
            override val ts: LocalDateTime
    ) : OwnedString(owner, topic, ts) {
        constructor(json: JsonObject) : this(
                User[json["owner"].asString],
                json["value"].asString,
                json["last_set"].asTimestamp
        )
    }
}
