package com.deanveloper.slak.event

import java.util.*
import kotlin.reflect.KClass

object EventHandler {
    private val _handlers = HashMap<KClass<out Event>, MutableSet<(Event) -> Unit>>()
    val idMap = HashMap<Int, (Event) -> Unit>()

    /**
     * @param event     Class of the event you are registering
     * @param handler   The event handler
     *
     * @return The id of the handler (used to unregister if you want)
     */
    inline fun <reified T : Event> register(noinline handler: (T) -> Unit): Int {
        var id: Int = 0
        while (idMap[id] == null) {
            id++
        }
        val list = _handlers.getOrPut(T::class, { mutableSetOf() })
        @Suppress("UNCHECKED_CAST")
        val realHandler = { e: Event -> handler(e as T) }
        list.add(realHandler)
        idMap[id] = realHandler
        return id
    }

    /**
     * @param event Class of the event
     * @param id    The id of the event handler
     */
    fun unregister(event: KClass<out Event>, id: Int) {
        val handler = idMap[id]
        _handlers.getOrDefault(event, mutableSetOf()).remove(handler)
        idMap.remove(id)
    }

    fun callEvent(event: Event) {
        _handlers[event.javaClass.kotlin]?.forEach { it(event) }
    }
}

