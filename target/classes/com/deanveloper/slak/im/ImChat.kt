package com.deanveloper.slak.im

import com.deanveloper.slak.*
import com.deanveloper.slak.message.Message
import com.deanveloper.slak.util.Cacher
import com.deanveloper.slak.util.ErrorHandler
import java.time.LocalDateTime
import java.util.*

class ImChat private constructor(
        val id: String,
        val user: User,
        val created: LocalDateTime,
        var isUserDeleted: Boolean
) {
    init {
        ImManager.put(id, this)
        ImManager.put(user.id, this)
    }

    companion object ImManager : Cacher<ImChat>() {
        fun start() {
            val json = runMethodSync("im.list", "token" to TOKEN, "exclude_archived" to "0")
            for (elem in json["ims"].asJsonArray) {
                with(elem.asJsonObject) {
                    ImChat(
                            this["id"].asString,
                            User[this["user"].asString],
                            this["created"].asTimestamp,
                            this["is_user_deleted"].asBoolean
                    )
                }
            }
        }

        fun open(user: User, cb: (ImChat) -> Unit): ErrorHandler {
            return runMethod("im.open", "token" to TOKEN) {
                for (json in it["channel"].asJsonArray) {
                    with(it.asJsonObject) {
                        ImChat(
                                it["id"].asString,
                                User[it["user"].asString],
                                it["created"].asTimestamp,
                                it["is_user_deleted"].asBoolean
                        )
                    }
                }
            }
        }

        val list: Set<ImChat>
            get() = this.values.toSet()
    }

    fun close(cb: (Boolean) -> Unit = { }): ErrorHandler {
        return runMethod("im.close", "token" to TOKEN, "channel" to id) {
            cb(it.has("no_op"))
        }
    }

    fun history(latest: Long = -1, oldest: Long = -1, inclusive: Boolean = false, count: Int = -1,
                unreads: Boolean = false, cb: (List<Message>) -> Unit): ErrorHandler {
        var params = ArrayList<Pair<String, String>>(5)
        params.add("token" to TOKEN)
        params.add("channel" to id)
        if (latest > 0) params.add("latest" to latest.toString())
        if (oldest > 0) params.add("oldest" to oldest.toString())
        if (inclusive) params.add("inclusive" to inclusive.toString())
        if (count > 0) params.add("count" to count.toString())
        if (unreads) params.add("unreads" to unreads.toString())

        return runMethod("im.history", "token" to TOKEN, "channel" to id) {
            cb(it["messages"].asJsonArray.map { Message(it.asJsonObject) })
        }
    }

    fun mark(msg: Message): ErrorHandler {
        return runMethod("im.mark", "token" to TOKEN, "channel" to id, "ts" to msg.ts.toTimestamp) {}
    }
}
