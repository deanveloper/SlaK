package com.deanveloper.slak

import com.deanveloper.slak.util.Cacher
import com.deanveloper.slak.util.ErrorHandler
import com.deanveloper.slak.util.runAsync
import com.google.gson.JsonObject
import java.awt.Color
import java.awt.Image
import java.net.URL
import java.util.*
import javax.imageio.ImageIO

/**
 * Represents a slack user
 *
 * @author Dean B
 */
class User private constructor(id: String,
                               name: String,
                               deleted: Boolean,
                               color: Color,
                               profile: Profile,
                               timezone: TimeZone,
                               isAdmin: Boolean,
                               isOwner: Boolean,
                               has2FA: Boolean,
                               hasFiles: Boolean) {
    val id: String
    var name: String
        private set
    var deleted: Boolean
        private set
    var color: Color
        private set
    val profile: Profile
    var timezone: TimeZone
        private set
    var isAdmin: Boolean
        private set
    var isOwner: Boolean
        private set
    var has2FA: Boolean
        private set
    var hasFiles: Boolean
        private set

    init {
        this.id = id
        this.name = name
        this.deleted = deleted
        this.color = color
        this.profile = profile
        this.timezone = timezone
        this.isAdmin = isAdmin
        this.isOwner = isOwner
        this.has2FA = has2FA
        this.hasFiles = hasFiles

        UserManager.put(if (name.startsWith('@')) name else "@$name", this)
        UserManager.put(id, this)
    }

    constructor(json: JsonObject) : this(json["id"].asString,
        json["name"].asString,
        json["deleted"].asBoolean,
        Color.getColor(json["color"].asString),
        Profile(json["profile"].asJsonObject),
        SimpleTimeZone(json["tz_offset"].asInt * 1000, json["tz_label"].asString),
        json["is_admin"].asBoolean,
        json["is_owner"].asBoolean,
        json["has_2fa"].asBoolean,
        json["has_files"].asBoolean
    )


    companion object UserManager : Cacher<String, User>() {
        fun start(): ErrorHandler {
            return runMethod("users.list", "token" to TOKEN) {
                for (json in it["members"].asJsonArray) {
                    User(json.asJsonObject)
                }
            }
        }
    }

    data class Profile(val firstName: String?, val lastName: String?, val realName: String?, val email: String?,
                       val skype: String?, val phone: String?, val imageSmallUrl: String, val imageLargeUrl: String) {
        lateinit var imageSmall: Image
            private set
        lateinit var imageLarge: Image
            private set

        init {
            runAsync { imageSmall = ImageIO.read(URL(imageSmallUrl)) }
            runAsync { imageLarge = ImageIO.read(URL(imageLargeUrl)) }
        }

        constructor(json: JsonObject) : this(json["first_name"].asString, json["last_name"].asString,
            json["real_name"].asString, json["email"].asString, json["skype"].asString, json["phone"].asString,
            json["image_32"].asString, json["image_192"].asString)
    }
}
