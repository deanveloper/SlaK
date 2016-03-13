package org.hopkinsschools.jarvis.slackapi.message

import org.hopkinsschools.jarvis.slackapi.User
import java.time.Instant

interface Message<T> {
    val owner: User;
    val ts: Instant;
    val message: T;
}