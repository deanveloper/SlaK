package com.deanveloper.slak.channel

import com.deanveloper.slak.TOKEN
import com.deanveloper.slak.User
import com.deanveloper.slak.message.Message
import com.deanveloper.slak.message.SimpleMessage
import com.deanveloper.slak.runMethod
import com.deanveloper.slak.util.Cacher
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*

/**
 * Base class for all channel-type classes
 *
 * @author Dean B
 */
abstract class BaseChannel<T : BaseChannel<T>> internal constructor(
		val id: String,
		var name: String,
		val created: LocalDateTime,
		var archived: Boolean,
		val general: Boolean,
		val members: List<User>?,
		val topic: OwnedString.Topic,
		val purpose: OwnedString.Purpose,
		val methodBase: String
) {
	init {
		name = if (name.startsWith('#')) name else "#$name"
		handler.put(name, this as T)//"this" is now smart casted to T
		handler.put(id, this)
	}

	abstract val handler: ChannelCompanion

	abstract inner class ChannelCompanion : Cacher<String, T>() {
		abstract fun fromJson(json: JsonObject): T

		fun register() {
			runMethod("channels.list", "token" to TOKEN, "exclude_archived" to "0") {
				for (json in it["channels"].asJsonArray) {
					fromJson(json.asJsonObject)
				}
			}
		}

		fun create(channel: String, cb: ((T) -> Unit)? = null) {
			runMethod("channels.create", "name" to channel, "token" to TOKEN) { json ->
				val created = fromJson(json["channel"].asJsonObject)
				cb?.invoke(created)
			}
		}
	}


	fun archive(cb: () -> Unit) {
		runMethod("$methodBase.archive", "token" to TOKEN, "channel" to id) {
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

		runMethod("$methodBase.history", *params.toTypedArray()) {
			cb.invoke(it["messages"].asJsonArray.map { SimpleMessage(it.asJsonObject) })
		}
	}

	fun invite(user: User, cb: ((T) -> Unit)? = null) {
		runMethod("$methodBase.invite", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
			cb?.invoke(handler[json.getAsJsonObject("channel")["id"].asString])
		}
	}

	fun join(cb: ((T) -> Unit)? = null) {
		runMethod("$methodBase.join", "token" to TOKEN, "name" to name) { json ->
			cb?.invoke(handler[json.getAsJsonObject("channel")["id"].asString])
		}
	}

	fun kick(user: User, cb: (() -> Unit)? = null) {
		runMethod("$methodBase.kick", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
			cb?.invoke()
		}
	}

	fun leave(cb: (() -> Unit)?) {
		runMethod("$methodBase.leave", "token" to TOKEN, "channel" to id) { json ->
			cb?.invoke()
		}
	}

	val list: Collection<T>
		get() = handler.values

	fun setPurpose(purpose: String, cb: ((String) -> Unit)?) {
		runMethod("$methodBase.setPurpose", "token" to TOKEN, "channel" to id, "purpose" to purpose) {
			json ->
			cb?.invoke(json["purpose"].asString)
		}
	}

	fun setTopic(topic: String, cb: ((String) -> Unit)?) {
		runMethod("$methodBase.setTopic", "token" to TOKEN, "channel" to id, "topic" to topic) {
			json ->
			cb?.invoke(json["topic"].asString)
		}
	}
}