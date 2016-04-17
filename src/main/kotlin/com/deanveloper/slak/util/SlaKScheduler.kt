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


private val executor = Executors.newCachedThreadPool();

fun runAsync(task: () -> Unit) {
	executor.submit(task);
}

fun runLater(delay: Duration, task: () -> Unit) {
	runTimer(delay, Duration.ofSeconds(-1), task);
}

fun runTimer(delay: Duration, repeat: Duration, task: () -> Unit) {
	executor.submit {
		var execute: LocalDateTime = LocalDateTime.now() + delay;
		while (true) {
			while (execute <= LocalDateTime.now());
			task();

			if (repeat < Duration.ZERO) break;
			execute += repeat;
		}
	}
}