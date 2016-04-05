package com.deanveloper.slak.util

import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.Executors

/**
 * Utility to run tasks asynchronously.
 *
 * @author Dean B
 */
object SlaKScheduler {
	private val executor = Executors.newCachedThreadPool();

	fun submit(task: () -> Unit) {
		executor.submit(task);
	}

	fun submitLater(delay: Duration, task: () -> Unit) {
		submitTimer(delay, Duration.ofSeconds(-1), task);
	}

	fun submitTimer(delay: Duration, repeat: Duration, task: () -> Unit) {
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
}