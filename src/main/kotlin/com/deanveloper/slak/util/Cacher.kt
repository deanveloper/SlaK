package com.deanveloper.slak.util

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Represents the Companion Object for a cachable class.
 *
 * @author Dean B
 */
open class Cacher<K, V> {
    private val lock = ReentrantReadWriteLock()
    private val cached: MutableMap<K, V> = mutableMapOf()

    operator fun get(index: K) = lock.read { cached[index] ?: throw RuntimeException("Index not found!") }

    internal fun put(index: K, value: V) = lock.write { cached.put(index, value) }

    internal fun remove(index: K) = lock.write { cached.remove(index) }

    val keys: Set<K>
        get() = lock.read { cached.keys }

    val values: Collection<V>
        get() = lock.read { cached.values }
}
