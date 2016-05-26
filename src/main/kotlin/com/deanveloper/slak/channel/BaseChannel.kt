package com.deanveloper.slak.channel

import com.deanveloper.slak.TOKEN
import com.deanveloper.slak.User
import com.deanveloper.slak.message.Message
import com.deanveloper.slak.runMethod
import com.deanveloper.slak.util.Cacher
import com.deanveloper.slak.util.ErrorHandler
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*

/**
 * Base class for all channel-type classes
 *
 * @author Dean B
 */
abstract class BaseChannel<T : BaseChannel<T>> protected constructor(
        id: String,
        name: String,
        creator: User,
        created: LocalDateTime,
        archived: Boolean,
        general: Boolean,
        members: List<User>,
        topic: OwnedString.Topic?,
        purpose: OwnedString.Purpose?,
        handler: ChannelCompanion<T>
) {
    val id = id
    var name = name
        private set
    val creator = creator
    val created = created
    var archived = archived
        private set
    val general = general
    var topic = topic
        private set
    var purpose = purpose
        private set
    var members = members
        private set
    val handler = handler

    init {
        this.name = if (name.startsWith('#')) name else "#$name"
        handler.put(name, this as T)//"this" is now smart casted to T
        handler.put(id, this)
    }

    abstract class ChannelCompanion<T : BaseChannel<T>>(val methodBase: String) : Cacher<T>() {
        abstract protected fun fromJson(json: JsonObject): T

        inline fun start(crossinline cb: () -> Unit): ErrorHandler {
            return runMethod("$methodBase.list", "token" to TOKEN, "exclude_archived" to "0") {
                println("[$methodBase] callback called")
                for (json in it["$methodBase"].asJsonArray) {
                    println("[$methodBase] looping through array element")
                    fromJson(json.asJsonObject)
                    println("[$methodBase] json parsed")
                }

                cb()
            }
        }

        @JvmOverloads fun create(channel: String, cb: (T) -> Unit = { }): ErrorHandler {
            return runMethod("$methodBase.create", "name" to channel, "token" to TOKEN) { json ->
                val created = fromJson(json["${methodBase.substring(0, methodBase.length - 1)}"].asJsonObject)
                cb(created)
            }
        }

        val list: Set<T>
            get() = this.values.toSet()
    }


    @JvmOverloads fun archive(cb: (Boolean) -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.archive", "token" to TOKEN, "channel" to id) {
            cb(it.has("no_op"))
        }
    }

    fun history(latest: Long = -1, oldest: Long = -1, inclusive: Boolean = false, count: Int = -1,
                unreads: Boolean = false, cb: (List<Message>) -> Unit): ErrorHandler {
        val params = ArrayList<Pair<String, String>>(5)
        params.add("token" to TOKEN)
        params.add("channel" to id)
        if (latest > 0) params.add("latest" to latest.toString())
        if (oldest > 0) params.add("oldest" to oldest.toString())
        if (inclusive) params.add("inclusive" to inclusive.toString())
        if (count > 0) params.add("count" to count.toString())
        if (unreads) params.add("unreads" to unreads.toString())

        return runMethod("${handler.methodBase}.history", *params.toTypedArray()) {
            cb(it["messages"].asJsonArray.map { Message(it.asJsonObject) })
        }
    }

    @JvmOverloads fun invite(user: User, cb: (T) -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.invite", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
            cb(handler[json.getAsJsonObject("channel")["id"].asString])
        }
    }

    @JvmOverloads fun join(cb: (T) -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.join", "token" to TOKEN, "name" to name) { json ->
            cb(handler[json.getAsJsonObject("channel")["id"].asString])
        }
    }

    @JvmOverloads fun kick(user: User, cb: () -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.kick", "token" to TOKEN, "channel" to id, "user" to user.id) { json ->
            cb()
        }
    }

    @JvmOverloads fun leave(cb: () -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.leave", "token" to TOKEN, "channel" to id) { json ->
            cb()
        }
    }

    @JvmOverloads fun setPurpose(purpose: String, cb: (String) -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.setPurpose", "token" to TOKEN, "channel" to id, "purpose" to purpose) {
            json ->
            cb(json["purpose"].asString)
        }
    }

    @JvmOverloads fun setTopic(topic: String, cb: (String) -> Unit = { }): ErrorHandler {
        return runMethod("${handler.methodBase}.setTopic", "token" to TOKEN, "channel" to id, "topic" to topic) {
            json ->
            cb(json["topic"].asString)
        }
    }
}
