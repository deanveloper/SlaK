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
class Channel(id: String, name: String, created: Instant, archived: Boolean, general: Boolean, members: List<User>?,
              topic: Channel.OwnedString, purpose: Channel.OwnedString) {
    val id = id;
    val name = name;

    val created = created;
    val general = general;
    var archived = archived
        private set;

    val members = members;

    var topic: OwnedString = topic
        private set;
    var purpose: OwnedString = purpose
        private set;

    init {
        channels[if (name.startsWith('@')) name else "#$name"] = this;
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

    fun invite(user: User, cb: ((Channel) -> Unit)? = null) {
        SlackAPI.runMethod("channels.invite", "token" to SlackAPI.TOKEN, "channel" to id, "user" to user.id) { json ->
            cb?.invoke(Channel[json.getAsJsonObject("channel")["id"].asString]!!);
        }
    }

    fun join(cb: ((Channel) -> Unit)? = null) {
        SlackAPI.runMethod("channels.join", "token" to SlackAPI.TOKEN, "name" to name) { json ->
            cb?.invoke(Channel[json.getAsJsonObject("channel")["id"].asString]!!);
        }
    }

    fun kick(user: User, cb: (() -> Unit)? = null) {
        SlackAPI.runMethod("channels.kick", "token" to SlackAPI.TOKEN, "channel" to id, "user" to user.id) { json ->
            cb?.invoke();
        }
    }

    fun leave(cb: (() -> Unit)?) {
        SlackAPI.runMethod("channels.leave", "token" to SlackAPI.TOKEN, "channel" to id) { json ->
            cb?.invoke();
        }
    }

    fun list() = setOf(*channels.values.toTypedArray());

    fun setPurpose(purpose: String, cb: ((String) -> Unit)?) {
        SlackAPI.runMethod("channels.setPurpose", "token" to SlackAPI.TOKEN, "channel" to id, "purpose" to purpose) {
            json ->
            cb?.invoke(json["purpose"].asString);
        }
    }

    fun setTopic(topic: String, cb: ((String) -> Unit)?) {
        SlackAPI.runMethod("channels.setTopic", "token" to SlackAPI.TOKEN, "channel" to id, "topic" to topic) {
            json ->
            cb?.invoke(json["topic"].asString);
        }
    }

    data class OwnedString(val value: String, val owner: User, val setAt: Instant) {
        private constructor(value: JsonElement, owner: JsonElement, time: JsonElement) :
        this(value.asString, User[owner.asString]!!, Instant.ofEpochSecond(time.asLong))

        constructor(json: JsonObject) : this(json["value"], json["owner"], json["last_set"]);
    }
}

