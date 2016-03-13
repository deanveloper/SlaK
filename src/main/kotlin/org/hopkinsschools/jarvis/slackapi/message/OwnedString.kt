package org.hopkinsschools.jarvis.slackapi.message

import com.google.gson.JsonObject
import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

class OwnedString(message: String, owner: User, ts: Instant) : Message<String> {
    override val owner = owner;
    override val ts = ts;
    override val message = message;

    constructor(json: JsonObject) : this(
            json["value"].asString,
            User[json["owner"].asString]!!,
            Instant.ofEpochSecond(json["last_set"].asLong)
    );

    override fun toString() = "owner=$owner, ts=$ts, message=$message";
}