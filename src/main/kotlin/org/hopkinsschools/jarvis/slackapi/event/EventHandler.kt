package org.hopkinsschools.jarvis.slackapi.event

import java.util.*
import kotlin.reflect.KClass

class EventHandler {
    companion object {
        val eventHandlers = HashMap<KClass<in Event>, (Event) -> Unit>()
    }
}

