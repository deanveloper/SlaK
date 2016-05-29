/**
 * Utility to run tasks asynchronously.
 *
 * @author Dean B
 */
@file:JvmName("SlaKScheduler")

package com.deanveloper.slak.util

import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask


private val executor = Executors.newCachedThreadPool()
private val forkJoin = ForkJoinPool()

fun runAsync(task: () -> Unit) {
    executor.submit(task)
}

fun runLater(delay: Duration, task: () -> Unit) {
    runTimer(delay, Duration.ofSeconds(-1), task)
}

fun runTimer(delay: Duration, repeat: Duration, task: () -> Unit) {
    runAsync {
        var execute: LocalDateTime = LocalDateTime.now() + delay
        while (true) {
            while (execute <= LocalDateTime.now())
                task();

            if (repeat < Duration.ZERO) break
            execute += repeat
        }
    }
}

fun <T> parallel(tasks: List<() -> T>): List<T> {
    val data = mutableListOf<T>()
    var completed: Int = 0

    tasks.forEach {
        runAsync {
            try {
                data.add(it())
            } catch(e: Throwable) {
                e.printStackTrace()
            }
            completed++
        }
    }

    while (completed < tasks.size);
    return data
}

fun <T> parallel(forkJoinTask: ForkJoinTask<T>): T {
    return forkJoin(forkJoinTask)
}
