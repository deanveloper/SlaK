package org.hopkinsschools.jarvis.slackapi

import com.google.gson.JsonObject
import java.awt.Color
import java.awt.Image
import java.net.URL
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.imageio.ImageIO
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Represents a slack user
 *
 * @author Dean Bassett
 */
data class User(val id: String,
                var name: String,
                var deleted: Boolean,
                val color: Color,
                val profile: Profile,
                var timezone: TimeZone,
                var isAdmin: Boolean,
                var isOwner: Boolean,
                var has2FA: Boolean,
                var hasFiles: Boolean) {

    init {
        if(lock.isWriteLockedByCurrentThread) {
            users[if (name.startsWith('@')) name else "@$name"] = this;
            users[id] = this;
        } else {
            lock.write {
                users[if (name.startsWith('@')) name else "@$name"] = this;
                users[id] = this;
            }
        }
    }

    constructor(json: JsonObject) : this(json["id"].asString, json["name"].asString, json["deleted"].asBoolean,
            Color.getColor(json["color"].asString), Profile(json["profile"].asJsonObject),
            SimpleTimeZone(json["tz_offset"].asInt * 1000, json["tz_label"].asString), json["is_admin"].asBoolean,
            json["is_owner"].asBoolean, json["has_2fa"].asBoolean, json["has_files"].asBoolean);


    companion object {
        private val users = HashMap<String, User>();
        private val lock = ReentrantReadWriteLock();

        operator fun get(index: String): User? {
            lock.read {
                return users[index]; //inline lambda, `return` returns to `get` method
            }
        }

        fun register() {
            SlackAPI.runMethod("users.list", Pair("token", SlackAPI.TOKEN)) {
                lock.write {
                    for (json in it["members"].asJsonArray) {
                        User(json.asJsonObject);
                    }
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
            SlackScheduler.submit { imageSmall = ImageIO.read(URL(imageSmallUrl)) };
            SlackScheduler.submit { imageLarge = ImageIO.read(URL(imageLargeUrl)) };
        }

        constructor(json: JsonObject) : this(json["first_name"].asString, json["last_name"].asString,
                json["real_name"].asString, json["email"].asString, json["skype"].asString, json["phone"].asString,
                json["image_32"].asString, json["image_192"].asString);
    }
}