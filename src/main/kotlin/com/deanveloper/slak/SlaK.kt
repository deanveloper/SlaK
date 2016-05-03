@file:JvmName("SlaK")

/**
 * Util methods for a slack API
 *
 * @author Dean B
 */
package com.deanveloper.slak

import com.deanveloper.slak.util.LateInitVal
import com.deanveloper.slak.util.runAsync
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


var TOKEN: String by LateInitVal()
var BASE_URL: String by LateInitVal()
val PARSER = JsonParser()

fun runMethod(method: String, vararg params: Pair<String, String>, onError: (SlaKError) -> Unit = {},
              onWarning: (String) -> Unit = {}, cb: (JsonObject) -> Unit = {}) {
	runAsync {
		try {
			val website = URL(BASE_URL + method + params.format()).openConnection()
			val reader = BufferedReader(InputStreamReader(website.inputStream))

			val json = PARSER.parse(reader).asJsonObject
			if (json["ok"].asBoolean) {
				cb.invoke(json)
			} else {
				val error: SlaKError
				try {
					error = SlaKError.valueOf(json["error"].asString)
				} catch (e: Exception) {
					error = SlaKError.UNDOCUMENTED_ERROR
					println(json["error"].asString)
				}
				onError.invoke(error)
			}

			//if there's a warning, invoke onWarning
			json["warning"]?.let { onWarning.invoke(it.asString) }
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}

/**
 * Turns an array of paired strings into a single formatted string.
 */
private fun Array<out Pair<String, String>>.format(): String {
	return buildString {
		for ((key, value) in this@format) {
			append("$key&${value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")}")
			this.append('&')
		}
		deleteCharAt(length - 1)
	}
}

val JsonElement.asTimestamp: LocalDateTime get() {
	if (this.isJsonPrimitive) {
		val time: Instant
		if (this.asJsonPrimitive.isString) {
			val split = this.asString.split("\\.")[0]
			time = Instant.ofEpochSecond(split[0].toLong()).plusMillis(split[1].toLong())
		} else if (this.asJsonPrimitive.isNumber) {
			time = Instant.ofEpochSecond(this.asLong)
		} else {
			throw ClassCastException("JsonElement is not a String or Number!")
		}

		return LocalDateTime.ofInstant(time, ZoneId.of("UTC"))
	} else {
		throw ClassCastException("JsonElement is not a String or Number!")
	}
}