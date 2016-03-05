package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonObject
import java.awt.Color
import java.awt.Image
import java.net.URL
import java.util.*
import javax.imageio.ImageIO

/**
 * Placeholder class
 *
 * @author Dean Bassett
 */
class User(val id: String, val name: String, val deleted: Boolean, val color: Color, val profile: Profile,
           val isAdmin: Boolean, val isOwner: Boolean, val has2FA: Boolean, val hasFiles: Boolean) {

    init {
        users[if (name.startsWith('@')) name else "@$name"] = this;
        users[id] = this;
    }

    constructor(json: JsonObject) : this(json["id"].asString, json["name"].asString, json["deleted"].asBoolean,
            Color.getColor(json["color"].asString), Profile(json["profile"].asJsonObject), json["is_admin"].asBoolean,
            json["is_owner"].asBoolean, json["has_2fa"].asBoolean, json["has_files"].asBoolean);


    companion object {
        private val users = HashMap<String, User>();
        operator fun get(index: String): User? = users[index];

        fun register() {
            SlackAPI.runMethod("users.list", Pair("token", SlackAPI.TOKEN)) {
                for (json in it["members"].asJsonArray) {
                    User(json.asJsonObject);
                }
            }
        }
    }

    data class Profile(val firstName: String?, val lastName: String?, val realName: String?, val email: String?,
                  val skype: String?, val phone: String?, val imageSmallUrl: String, val imageLargeUrl: String) {
        lateinit var imageSmall: Image
            private set;
        lateinit var imageLarge: Image
            private set;

        init {
            SlackAPI.executor.submit { imageSmall = ImageIO.read(URL(imageSmallUrl)) };
            SlackAPI.executor.submit { imageLarge = ImageIO.read(URL(imageLargeUrl)) };
        }

        constructor(json: JsonObject) : this(json["first_name"].asString, json["last_name"].asString,
                json["real_name"].asString, json["email"].asString, json["skype"].asString, json["phone"].asString,
                json["image_32"].asString, json["image_192"].asString);
    }
}