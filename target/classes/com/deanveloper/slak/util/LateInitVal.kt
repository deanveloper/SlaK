package com.deanveloper.slak.util

import kotlin.reflect.KProperty

/**
 * Delegates a variable to only be initialized once
 *
 * @author Dean B
 */
class LateInitVal<T> {
    var initialized = false
    var value: T? = null

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: T) {
        if (initialized) {
            throw IllegalStateException("${prop.name} is already initialized!")
        } else {
            this.value = value
            initialized = true
        }
    }

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        return if (initialized) value as T else throw IllegalStateException("${prop.name} is not yet initialized!")
    }
}
