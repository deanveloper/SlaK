package com.deanveloper.slak

import com.deanveloper.slak.util.Cacher
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
data class User private constructor(val id: String,
                                    var name: String,
                                    var deleted: Boolean,
                                    var color: Color,
                                    val profile: Profile,
                                    var timezone: TimeZone,
                                    var isAdmin: Boolean,
                                    var isOwner: Boolean,
                                    var has2FA: Boolean,
                                    var hasFiles: Boolean) {

	init {
		UserManager.put(if (name.startsWith('@')) name else "@$name", this)
		UserManager.put(id, this)
	}

	constructor(json: JsonObject) : this(json["id"].asString, json["name"].asString, json["deleted"].asBoolean,
			Color.getColor(json["color"].asString), Profile(json["profile"].asJsonObject),
			SimpleTimeZone(json["tz_offset"].asInt * 1000, json["tz_label"].asString), json["is_admin"].asBoolean,
			json["is_owner"].asBoolean, json["has_2fa"].asBoolean, json["has_files"].asBoolean)


	companion object UserManager : Cacher<String, User>() {
		fun start() {
			runMethod("users.list", "token" to TOKEN) {
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