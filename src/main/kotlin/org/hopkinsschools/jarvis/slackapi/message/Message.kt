package org.hopkinsschools.jarvis.slackapi.message

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant
import java.time.LocalDateTime

interface Message<T> {
    val type: String;
    val owner: User;
    val message: T;
    val ts: LocalDateTime;
    val starred: Boolean;
    val reactions: Array<Reaction>;


    class Reaction (val name: String, var users: Array<User>);
}

