package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.hopkinsschools.jarvis.slackapi.channel.Channel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.io.StringWriter
import java.net.URL
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.Executors

/**
 * Util methods for a slack API
 *
 * @author Dean Bassett
 */
object SlackAPI {
    const val TOKEN = "";
    const val BASE_URL = "https://2239technocrats.slack.com/api/";
    val parser = JsonParser();

    fun runMethod(method: String, vararg params: Pair<String, String>, onError: ((String) -> Unit)? = null,
                  onWarning: ((String) -> Unit)? = null, cb: ((JsonObject) -> Unit)? = null) {
        SlackScheduler.submit {
            try {
                val website = URL(BASE_URL + method + params.format()).openConnection();
                val reader = BufferedReader(InputStreamReader(website.inputStream));

                val json = parser.parse(reader).asJsonObject;
                if (json["ok"].asBoolean) {
                    cb?.invoke(json)
                } else {
                    if (onError == null)
                        runMethod("chat.postMessage", Pair("token", TOKEN), Pair("channel", Channel["#random"]!!.id),
                                Pair("text", "Server side error!\n>${json["error"].asString}"));
                    else
                        onError.invoke(json["error"].asString);
                }

                //if there's a warning, invoke onWarning
                json["warning"]?.let { onWarning?.invoke(it.asString) };
            } catch (e: Exception) {
                val sw = StringWriter();
                e.printStackTrace(PrintWriter(sw));
                val stack = sw.toString();
                runMethod("chat.postMessage", Pair("token", TOKEN), Pair("channel", Channel["#random"]!!.id),
                        Pair("text", "Uh oh, the bot encountered an error!\n$stack"));
                e.printStackTrace();
            }
        }
    }

    fun JsonElement.getAsTimestamp(): LocalDateTime {
        if (this.isJsonPrimitive) {
            val time: Instant;
            if (this.asJsonPrimitive.isString) {
                val split = this.asString.split("\\.")[0];
                time = Instant.ofEpochSecond(split[0].toLong()).plusMillis(split[1].toLong());
            } else if (this.asJsonPrimitive.isNumber) {
                time = Instant.ofEpochSecond(this.asLong);
            } else {
                throw ClassCastException("JsonElement is not a String or Number!");
            }

            return LocalDateTime.ofInstant(time, ZoneId.of("UTC"));
        } else {
            throw ClassCastException("JsonElement is not a String or Number!");
        }
    }

    private fun Array<out Pair<String, String>>.format(): String {
        val sb = StringBuilder("?");

        for ((key, value) in this) {
            sb.append("$key&${value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")}");
            sb.append('&');
        }
        sb.deleteCharAt(sb.length - 1);

        return sb.toString();
    }
}