package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.io.StringWriter
import java.net.URL
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
                                Pair("text", "Server side error!\n${json["error"].asString}"));
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
    private fun Array<out Pair<String, String>>.format(): String {
        val sb = StringBuilder("?");

        for ((key, value) in this) {
            sb.append("$key&$value");
            sb.append('&');
        }
        sb.deleteCharAt(sb.length - 1);

        return sb.toString();
    }
}