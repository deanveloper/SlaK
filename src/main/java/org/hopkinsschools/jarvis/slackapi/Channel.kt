package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.time.Instant
import java.util.*

/**
 * API for channels
 *
 * @author Dean Bassett
 */
data class Channel(val id: String, val name: String, val created: Instant, val archived: Boolean,
                   val general: Boolean, val members: List<User>?, val topic: Channel.OwnedString,
                   val purpose: Channel.OwnedString) {
    init {
        channels[if(name.startsWith('@')) name else "#$name"] = this;
        channels[id] = this;
    }

    constructor(json: JsonObject) : this(json["id"].asString, json["name"].asString,
            Instant.ofEpochSecond(json["created"].asLong), json["is_archived"].asBoolean,
            json["is_general"].asBoolean, toUserList(json["members"]?.asJsonArray),
            OwnedString(json["topic"].asJsonObject), OwnedString(json["purpose"].asJsonObject));


    companion object {
        //stores the id (ie B5LD982) and the name (ie #random)
        private val channels = HashMap<String, Channel>();

        operator fun get(index: String) = channels[index];

        fun register() {
            SlackAPI.runMethod("channels.list", Pair("token", SlackAPI.TOKEN), Pair("exclude_archived", "0")) {
                for (json in it["channels"].asJsonArray) {
                    Channel(json.asJsonObject);
                }
            }
        }

        fun create(channel: String, cb: ((Channel) -> Unit)? = null) {
            SlackAPI.runMethod("channels.create", Pair("name", channel), Pair("token", SlackAPI.TOKEN)) { json ->
                val created = Channel(json["channel"].asJsonObject);
                cb?.invoke(created);
            }
        }

        private fun toUserList(array: JsonArray?) = array?.map { User[it.asString]!! };
    }

    //channels.archive cannot be called by a bot

    fun history(latest: Long = -1, oldest: Long = -1, inclusive: Boolean = false, count: Int = -1,
                unreads: Boolean = false, cb: (List<OwnedString>) -> Unit) {
        var params = ArrayList<Pair<String, String>>(5);
        params.add(Pair("token", SlackAPI.TOKEN));
        params.add(Pair("channel", id));
        if (latest > 0) params.add(Pair("latest", latest.toString()));
        if (oldest > 0) params.add(Pair("oldest", oldest.toString()));
        if (inclusive) params.add(Pair("inclusive", inclusive.toString()));
        if (count > 0) params.add(Pair("count", count.toString()));
        if (unreads) params.add(Pair("unreads", unreads.toString()));

        SlackAPI.runMethod("channels.history", *params.toTypedArray()) {
            cb.invoke(it["messages"].asJsonArray.map { OwnedString(it.asJsonObject) });
        }
    }

    data class OwnedString(val value: String, val owner: User, val setAt: Instant) {
        private constructor(value: JsonElement, owner: JsonElement, time: JsonElement) :
        this(value.asString, User[owner.asString]!!, Instant.ofEpochSecond(time.asLong))

        constructor(json: JsonObject) : this(json["value"], json["owner"], json["last_set"]);
    }
}

