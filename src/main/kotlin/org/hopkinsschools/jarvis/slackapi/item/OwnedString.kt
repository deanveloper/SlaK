package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.time.Instant

data class OwnedString(val value: String, val owner: User, val setAt: Instant) {
    private constructor(value: JsonElement, owner: JsonElement, time: JsonElement) :
    this(value.asString, User[owner.asString]!!, Instant.ofEpochSecond(time.asLong))

    constructor(json: JsonObject) : this(json["value"], json["owner"], json["last_set"]);
}