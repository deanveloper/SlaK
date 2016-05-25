package com.deanveloper.slak.im

import com.deanveloper.slak.*
import com.deanveloper.slak.channel.OwnedString
import com.deanveloper.slak.util.Cacher
import com.deanveloper.slak.util.ErrorHandler
import com.google.gson.JsonObject
import java.time.LocalDateTime

/**
 * Represents a direct message with multiple users
 *
 * @author Dean B
 */
class MpimChat private constructor(
        id: String,
        name: String,
        creator: User,
        created: LocalDateTime,
        archived: Boolean,
        members: List<User>,
        topic: OwnedString.Topic?,
        purpose: OwnedString.Purpose?
) {
    val id = id
    var name = name
        private set
    val creator = creator
    val created = created
    var archived = archived
        private set
    var topic = topic
        private set
    var purpose = purpose
        private set
    var members = members
        private set

    init {
        MpimManager.put(id, this)
        MpimManager.put(name, this)
    }

    companion object MpimManager : Cacher<MpimChat>() {
        fun start(cb: () -> Unit): ErrorHandler {
            return runMethod("mpim.list", "token" to TOKEN, "exclude_archived" to "0") {
                for (elem in it["groups"].asJsonArray) {
                    fromJson(elem.asJsonObject)
                }

                cb()
            }
        }

        fun fromJson(json: JsonObject): MpimChat {
            if (!(json["is_mpim"]?.asBoolean ?: false)) {
                throw IllegalArgumentException("json does not represent an mpim")
            }

            return MpimChat(json["id"].asString, json["name"].asString,
                    User[json["creator"].asString], json["created"].asTimestamp, json["is_archived"].asBoolean,
                    json["members"].nullSafe?.asUserList ?: emptyList(),
                    if (json["topic"].nullSafe == null) OwnedString.Topic(json["topic"].asJsonObject) else null,
                    if (json["purpose"].nullSafe == null) OwnedString.Purpose(json["purpose"].asJsonObject) else null)
        }
    }
}

