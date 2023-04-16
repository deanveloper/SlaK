package com.deanveloper.slak.util

import com.deanveloper.slak.SlaKError
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Error Handler
 *
 * @author Dean B
 */
class ErrorHandler {
    private val errorLock: Lock = ReentrantLock()
    private val warningLock: Lock = ReentrantLock()
    var error: ((SlaKError) -> Unit)? = null
        get() {
            lock(errorLock) {
                return field
            }
        }
        set(value) {
            lock(errorLock) {
                field = value
            }
        }
    var warning: ((String) -> Unit)? = null
        get() {
            lock(warningLock) {
                return field
            }
        }
        set(value) {
            lock(warningLock) {
                field = value
            }
        }

    fun onError(cb: (SlaKError) -> Unit): ErrorHandler {
        error = cb
        return this
    }

    fun onWarning(cb: (String) -> Unit): ErrorHandler {
        warning = cb
        return this
    }

    inline fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }
}
