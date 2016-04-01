package com.deanveloper.slackapi

/**
 * @author Dean B
 */
sealed class SlackException(val desc: String) {
	val name: String
		get() {
			val name = javaClass.simpleName.toCharArray();
			var isFirst: Boolean = true;

			return buildString {
				for (c in name) {
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