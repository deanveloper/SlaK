package com.deanveloper.slackapi.event

import java.util.*
import kotlin.reflect.KClass

object EventHandler {
    val handlers = HashMap<KClass<out Event>, MutableSet<(Event) -> Unit>>()
    val idMap = HashMap<Int, (Event) -> Unit>();

    /**
     * @param event     Class of the event you are registering
     * @param handler   The event handler
     */
    fun <T : Event> handle(event: KClass<T>, handler: (T) -> Unit): Int {
        var id: Int = 0;
        while (idMap[id] == null) {
            id++;
        }
        var list = handlers.getOrPut(event, { mutableSetOf() });
        val realHandler = {e: Event -> handler(e as T)};
        list.add(realHandler);
        idMap[id] = realHandler;
        return id;
    }

    /**
     * @param event Class of the event
     * @param id    The id of the event handler
     */
    fun unregister(event: KClass<out Event>, id: Int) {
        val handler = idMap[id];
        handlers.getOrDefault(event, mutableSetOf()).remove(handler)
        idMap.remove(id);
    }

    fun callEvent(event: Event) {
        handlers[event.javaClass.kotlin]?.forEach { it(event) }
    }
}

