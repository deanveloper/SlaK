package com.deanveloper.slak.message

import com.deanveloper.slak.User
import java.time.LocalDateTime

abstract class Message<T>() {
	abstract val type: String
	abstract val owner: User
	abstract val message: T
	abstract val ts: LocalDateTime
	open val starred: Boolean = false
	open var reactions: Array<Reaction> = emptyArray()

	data class Reaction(val name: String, var users: Array<User>)
}

