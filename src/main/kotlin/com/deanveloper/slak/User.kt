package com.deanveloper.slak

import com.deanveloper.slak.util.Cacher
import com.deanveloper.slak.util.ErrorHandler
import com.google.gson.JsonObject
import java.awt.Color
import java.net.URL
import java.util.*
import javax.imageio.ImageIO

/**
 * Represents a slack user
 *
 * @author Dean B
 */
class User private constructor(
        id: String,
        name: String,
        deleted: Boolean,
        color: Color,
        profile: Profile,
        timezone: TimeZone,
        isAdmin: Boolean,
        isOwner: Boolean,
        has2FA: Boolean,
        hasFiles: Boolean
) {
    val id: String
    var name: String
        internal set
    var deleted: Boolean
        internal set
    var color: Color
        internal set
    val profile: Profile
    var timezone: TimeZone
        internal set
    var isAdmin: Boolean
        internal set
    var isOwner: Boolean
        internal set
    var has2FA: Boolean
        internal set
    var hasFiles: Boolean
        internal set

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

    constructor(json: JsonObject) : this(
            json["id"].asString,
            json["name"].asString,
            json["deleted"]?.asBoolean ?: false,
            Color(Integer.parseInt(json["color"].nullSafe?.asString ?: "0", 16)),
            Profile(json["profile"].asJsonObject),
            SimpleTimeZone((json["tz_offset"].nullSafe?.asInt ?: 0) * 1000, json["tz_label"].nullSafe?.asString ?: "null"),
            json["is_admin"]?.asBoolean ?: false,
            json["is_owner"]?.asBoolean ?: false,
            json["has_2fa"]?.asBoolean ?: false,
            json["has_files"]?.asBoolean ?: false
    )


    companion object UserManager : Cacher<User>() {
        inline fun start(crossinline cb: () -> Unit): ErrorHandler {
            return runMethod("users.list", "token" to TOKEN) {
                for (json in it["members"].asJsonArray) {
                    try {
                        User(json.asJsonObject)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                cb()
            }
        }

        protected fun fromJson(json: JsonObject): User {
            if (json["is_user"]?.asBoolean ?: false) {
                throw IllegalArgumentException("json does not represent a User")
            }

            return User(
                    json["id"].asString,
                    json["name"].asString,
                    json["deleted"]?.asBoolean ?: false,
                    Color(Integer.parseInt(json["color"].nullSafe?.asString ?: "0", 16)),
                    Profile(json["profile"].asJsonObject),
                    SimpleTimeZone((json["tz_offset"].nullSafe?.asInt ?: 0) * 1000, json["tz_label"].nullSafe?.asString ?: "null"),
                    json["is_admin"]?.asBoolean ?: false,
                    json["is_owner"]?.asBoolean ?: false,
                    json["has_2fa"]?.asBoolean ?: false,
                    json["has_files"]?.asBoolean ?: false
            )
        }

        val list: Set<User>
            get() = this.values.toSet()
    }

    data class Profile(val firstName: String?, val lastName: String?, val realName: String?, val email: String?,
                       val skype: String?, val phone: String?, val imageSmallUrl: String, val imageLargeUrl: String) {
        val imageSmall by lazy { ImageIO.read(URL(imageSmallUrl)) }
        val imageLarge by lazy { ImageIO.read(URL(imageLargeUrl)) }

        constructor(json: JsonObject) : this(json["first_name"].nullSafe?.asString, json["last_name"].nullSafe?.asString,
                json["real_name"].nullSafe?.asString, json["email"].nullSafe?.asString, json["skype"].nullSafe?.asString, json["phone"].nullSafe?.asString,
                json["image_32"].asString, json["image_192"].asString)
    }

    override fun toString() = "User[id=$id,name=$name,deleted=$deleted,color=$color,profile=$profile," +
            "timezone=$timezone,isAdmin=$isAdmin,isOwner=$isOwner,has2FA=$has2FA,hasFiles=$hasFiles"

    override fun hashCode() = id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        return id == (other as? User)?.id
    }
}
