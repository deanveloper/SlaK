package com.deanveloper.slackapi

/**
 * @author Dean B
 */
enum class SlackException(val desc: String) {
	INVALID_ARRAY_ARGS("The method was passed a PHP-style array argument " +
			"(e.g. with a name like foo[7]). These are never valid with the Slack API."),
	INVALID_CHARSET("The method was called via a POST request, but the charset " +
			"specified in the Content-Type header was invalid. Valid charset names are: utf-8 iso-8859-1."),
	INVALID_FORM_DATA("The method was called via a POST request with Content-Type " +
			"application/x-www-form-urlencoded or multipart/form-data, but the form data was either missing or " +
			"syntactically invalid."),
	INVALID_POST_TYPE("The method was called via a POST request, but the specified " +
			"Content-Type was invalid. Valid types are: application/json application/x-www-form-urlencoded " +
			"multipart/form-data text/plain."),
	MISSING_POST_TYPE("The method was called via a POST request and included a data " +
			"payload, but the request did not include a Content-Type header."),
	REQUEST_TIMEOUT("The method was called via a POST request, but the POST data was " +
			"either missing or truncated."),
	NOT_AUTHED("No authentication token provided."),
	INVALID_AUTH("Invalid authentication token."),
	ACCOUNT_INACTIVE("Authentication token is for a deleted user or team."),
	CHANNEL_NOT_FOUND("Value passed for channel was invalid."),
	ALREADY_ARCHIVED("Channel has already been archived."),
	CANT_ARCHIVE_GENERAL("You cannot archive the general channel."),
	LAST_RA_CHANNEL("You cannot archive the last channel for a multi-channel guest."),
	RESTRICTED_ACTION("A team preference prevents the authenticated user from " +
			"using this method."),
	USER_IS_BOT("This method cannot be called by a bot user."),
	USER_IS_RESTRICTED("This method cannot be called by a restricted user or single " +
			"channel guest."),
	NAME_TAKEN("A channel cannot be created with the given name."),
	NO_CHANNEL("Value passed for name was empty."),
	INVALID_TS_LATEST("Value passed for latest was invalid."),
	INVALID_TS_OLDEST("Value passed for oldest was invalid."),
	USER_NOT_FOUND("Value passed for user was invalid."),
	CANT_INVITE_SELF("Authenticated user cannot invite themselves to a channel."),
	NOT_IN_CHANNEL("Authenticated user is not in the channel."),
	ALREADY_IN_CHANNEL("Invited user is already in the channel."),
	IS_ARCHIVED("Channel has been archived."),
	CANT_INVITE("User cannot be invited to this channel."),
	USER_IS_ULTRA_RESTRICTED("This method cannot be called by a single channel guest."),
	CANT_KICK_SELF("Authenticated user can't kick themselves from a channel."),
	CANT_KICK_FROM_GENERAL("User cannot be removed from #general."),
	CANT_KICK_FROM_LAST_CHANNEL("User cannot be removed from the last channel they're in."),
	CANT_LEAVE_GENERAL("Authenticated user cannot leave the general channel."),
	INVALID_TIMESTAMP("Value passed for timestamp was invalid."),
	TOO_LONG("Purpose/Topic was longer than 250 characters."),
	NOT_ARCHIVED("Channel is not archived."),
	MESSAGE_NOT_FOUND("No message exists with the requested timestamp."),
	CANT_DELETE_MESSAGE("Authenticated user does not have permission to delete " +
			"this message."),
	COMPLIANCE_EXPORTS_PREVENT_DELETION("Compliance exports are on, messages can not be " +
			"deleted"),
	MSG_TOO_LONG("Message text is too long."),
	NO_TEXT("No message text provided."),
	RATE_LIMITED("Application has posted too many messages, read the Rate Limit " +
			"documentation for more information"),
	CANT_UPDATE_MESSAGE("Authenticated user does not have permission to update this " +
			"message."),
	EDIT_WINDOW_CLOSED("The message/comment cannot be edited due to the team message " +
			"edit settings."),
	UNKNOWN_ERROR("There was a mysterious problem ending the user's Do Not Disturb " +
			"session."),
	SNOOZE_NOT_ACTIVE("Snooze is not active for this user and cannot be ended."),
	SNOOZE_END_FAILED("There was a problem setting the user's Do Not Disturb status."),
	MISSING_DURATION("No value provided for num_minutes."),
	SNOOZE_FAILED("There was a problem setting the user's Do Not Disturb status."),
	FILE_NOT_FOUND("The requested file could not be found."),
	FILE_DELETED("The requested file was previously deleted."),
	NO_COMMENT("The comment field was empty."),
	CANT_DELETE("The requested comment could not be deleted."),
	CANT_EDIT("The requested file could not be found."),
	CANT_DELETE_FILE("Authenticated user does not have permission to delete this file."),
	UNKNOWN_TYPE("Value passed for types was invalid."),
	POSTING_TO_GENERAL_CHANNEL_DENIED("An admin has restricted posting to the #general channel."),
	INVALID_CHANNEL("One or more channels supplied are invalid.");
	//TODO: groups and on
}