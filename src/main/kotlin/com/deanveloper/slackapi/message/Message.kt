package com.deanveloper.slackapi.message

import com.deanveloper.slackapi.User
import java.time.LocalDateTime

abstract class Message<T>(val type: String,
                          val owner: User,
                          val message: T,
                          val ts: LocalDateTime,
                          val starred: Boolean = false,
                          var reactions: Array<Reaction> = emptyArray()) {

	data class Reaction(val name: String, var users: Array<User>);
}

