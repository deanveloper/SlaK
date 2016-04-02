package com.deanveloper.slackapi

/**
 * @author Dean B
 */
sealed class SlackException(val desc: String) {
	val name: String;

	init {
		var isFirst: Boolean = true;

		name = buildString {
			for (c in javaClass.simpleName) {
				if (c.isUpperCase()) {
					if (!isFirst) {
						this.append('_');
					} else {
						isFirst = false;
					}

					this.append(c.toLowerCase());
				}
			}
		}
	}
}