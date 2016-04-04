package com.deanveloper.slackapi.util

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Represents the Companion Object for a cachable class
 *
 * @author Dean B
 */
open class Cacher<K, V> {
    private val lock = ReentrantReadWriteLock();
    private val cached: MutableMap<K, V> = mutableMapOf();

    open operator fun get(index: K) = lock.read { cached[index] ?: throw RuntimeException("Index not found!") };

    protected fun put(index: K, value: V) = lock.write { cached.put(index, value) };

    val values: Collection<V>
        get() = cached.values;

    val keys: Set<K>
        get() = cached.keys
}