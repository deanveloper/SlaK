package com.deanveloper.slak.channel

import com.deanveloper.slak.TOKEN
import com.deanveloper.slak.User
import com.deanveloper.slak.asTimestamp
import com.deanveloper.slak.message.Message
import com.deanveloper.slak.message.SimpleMessage
import com.deanveloper.slak.runMethod
import com.deanveloper.slak.util.Cacher
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*

/**
 * API for channels
 *
 * @author Dean B
 */
class Channel private constructor(val id: String,
                                  var name: String,
                                  val created: LocalDateTime,
                                  var archived: Boolean,
                                  val general: Boolean,
                                  val members: List<User>?,
                                  val topic: OwnedString.Topic,
                                  val purpose: OwnedString.Purpose) {

	init {
		ChannelManager.put(if (name.startsWith('@')) name else "#$name", this)
		ChannelManager.put(id, this)
	}

	constructor(json: JsonObject) : this(json["id"].asString, json["name"].asString,
			json["created"].asTimestamp, json["is_archived"].asBoolean,
			json["is_general"].asBoolean, toUserList(json["members"]?.asJsonArray),
			OwnedString.Topic(json["topic"].asJsonObject), OwnedString.Purpose(json["purpose"].asJsonObject))

	companion object ChannelManager : Cacher<String, Channel>() {
		fun register() {
			runMethod("channels.list", "token" to TOKEN, "exclude_archived" to "0") {
				for (json in it["channels"].asJsonArray) {
					Channel(json.asJsonObject)
				}
			}
		}

		fun create(channel: String, cb: ((Channel) -> Unit)? = null) {
			runMethod("channels.create", "name" to channel, "token" to TOKEN) { json ->
				val created = Channel(json["channel"].asJsonObject)
				cb?.invoke(created)
			}
		}

		private fun toUserList(array: JsonArray?) = array?.map { User[it.asString] }
	}

	fun archive(cb: () -> Unit) {
		runMethod("channels.archive", "token" to TOKEN, "channel" to id) {
			cb()
		}
	}

	fun history(latest: Long = -1, oldest: Long = -1, inclusive: Boolean = false, count: Int = -1,
	            unreads: Boolean = false, cb: (List<Message<*>>) -> Unit) {
		var params = ArrayList<Pair<String, String>>(5)
		params.add("token" to TOKEN)
		params.add("channel" to id)
		if (latest > 0) params.add("latest" to latest.toString())
		if (oldest > 0) params.add("oldest" to oldest.toString())
		if (inclusive) params.add("inclusive" to inclusive.toString())
		if (count > 0) params.add("count" to count.toString())
		if (unreads) params.add("unreads" to unreads.toString())

		runMethod("channels.history", *params.toTypedArray()) {
			cb.invoke(it["messages"].asJsonArray.map { SimpleMessage(it.asJsonObject) })
		}
	}

	fun invite(user: User, cb: ((Channel) -> Unit)? = null) {
		runMethod("channels.invite", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
			cb?.invoke(Channel[json.getAsJsonObject("channel")["id"].asString])
		}
	}

	fun join(cb: ((Channel) -> Unit)? = null) {
		runMethod("channels.join", "token" to TOKEN, "name" to name) { json ->
			cb?.invoke(Channel[json.getAsJsonObject("channel")["id"].asString])
		}
	}

	fun kick(user: User, cb: (() -> Unit)? = null) {
		runMethod("channels.kick", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
			cb?.invoke()
		}
	}

	fun leave(cb: (() -> Unit)?) {
		runMethod("channels.leave", "token" to TOKEN, "channel" to id) { json ->
			cb?.invoke()
		}
	}

	fun list() = setOf(*ChannelManager.values.toTypedArray())

	fun setPurpose(purpose: String, cb: ((String) -> Unit)?) {
		runMethod("channels.setPurpose", "token" to TOKEN, "channel" to id, "purpose" to purpose) {
			json ->
			cb?.invoke(json["purpose"].asString)
		}
	}

	fun setTopic(topic: String, cb: ((String) -> Unit)?) {
		runMethod("channels.setTopic", "token" to TOKEN, "channel" to id, "topic" to topic) {
			json ->
			cb?.invoke(json["topic"].asString)
		}
	}

}
