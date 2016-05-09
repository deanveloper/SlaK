package com.deanveloper.slak

/**
 * @author Dean B
 */
enum class SlaKError(val desc: String) {
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
    ALREADY_ARCHIVED("Channel/Group has already been archived."),
    CANT_ARCHIVE_GENERAL("You cannot archive the general channel."),
    LAST_RA_CHANNEL("You cannot archive the last channel for a multi-channel guest."),
    RESTRICTED_ACTION("A team preference prevents the authenticated user from " +
        "using this method."),
    USER_IS_BOT("This method cannot be called by a bot user."),
    USER_IS_RESTRICTED("This method cannot be called by a restricted user or single " +
        "channel guest."),
    NAME_TAKEN("A channel/group cannot be created with the given name."),
    NO_CHANNEL("Value passed for name was empty."),
    INVALID_TS_LATEST("Value passed for latest was invalid."),
    INVALID_TS_OLDEST("Value passed for oldest was invalid."),
    USER_NOT_FOUND("Value passed for user was invalid."),
    CANT_INVITE_SELF("Authenticated user cannot invite themselves to a channel/group."),
    NOT_IN_CHANNEL("Authenticated user is not in the channel/group."),
    ALREADY_IN_CHANNEL("Invited user is already in the channel/group."),
    IS_ARCHIVED("Channel/Group has been archived."),
    CANT_INVITE("User cannot be invited to this channel/group."),
    USER_IS_ULTRA_RESTRICTED("This method cannot be called by a single channel guest."),
    CANT_KICK_SELF("Authenticated user can't kick themselves from a channel/group."),
    CANT_KICK_FROM_GENERAL("User cannot be removed from #general."),
    CANT_KICK_FROM_LAST_CHANNEL("User cannot be removed from the last channel they're in."),
    CANT_LEAVE_GENERAL("Authenticated user cannot leave the general channel."),
    INVALID_TIMESTAMP("Value passed for timestamp was invalid."),
    TOO_LONG("Purpose/Topic was longer than 250 characters."),
    NOT_ARCHIVED("Channel/group is not archived."),
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
    INVALID_CHANNEL("One or more channels/groups supplied are invalid."),
    NOT_IN_GROUP("User or caller were are not in the group."),
    CANT_LEAVE_LAST_CHANNEL("Authenticated user cannot leave the last channel/group they are in."),
    LAST_MEMBER("Authenticated user is the last member of a group and cannot leave it."),
    USER_DOES_NOT_OWN_CHANNEL("Calling user does not own this DM channel."),
    USER_NOT_VISIBLE("The calling user is restricted from seeing the requested user."),
    USER_DISABLED("The user has been disabled."),
    USERS_LIST_NOT_SUPPLIED("Missing users in request."),
    NOT_ENOUGH_USERS("Needs at least 2 users to open."),
    TOO_MANY_USERS("Needs at most 8 users to open."),
    INVALID_CLIENT_ID("Value passed for client_id was invalid."),
    BAD_CLIENT_SECRET("Value passed for client_secret was invalid."),
    INVALID_CODE("Value passed for code was invalid."),
    BAD_REDIRECT_URI("Value passed for redirect_uri did not match the redirect_uri in the original request."),
    BAD_TIMESTAMP("Value passed for timestamp was invalid."),
    FILE_COMMENT_NOT_FOUND("File comment specified by file_comment does not exist."),
    NO_ITEM_SPECIFIED("A required argument was not specified."),
    ALREADY_PINNED("The specified item is already pinned to the channel."),
    PERMISSION_DENIED("The user does not have permission to add pins to the channel."),
    FILE_NOT_SHARED("File specified by file is not public nor shared to the channel."),
    NOT_PINNED("The specified item is not pinned to the channel."),
    INVALID_NAME("Value passed for name was invalid."),
    ALREADY_REACTED("The specified item already has the user/reaction combination."),
    TOO_MANY_EMOJI("The limit for distinct reactions (i.e emoji) on the item has been reached."),
    TOO_MANY_REACTIONS("The limit for distinct reactions (i.e emoji) on the item has been reached."),
    NO_REACTION("The specified item does not have the user/reaction combination."),
    MIGRATION_IN_PROGRESS("Team is being migrated between servers. See the team_migration_started event " +
        "documentation for details."),
    ALREADY_STARRED("The specified item has already been starred by the authenticated user."),
    NOT_STARRED("The specified item is not currently starred by the authenticated user."),
    PAID_ONLY("This is only available to paid teams."),
    OVER_PAGINATION_LIMIT("It is not possible to request more than 1000 items per page or more than 100 pages."),
    INVALID_PRESENCE("Value passed for presence was invalid."),
    UNDOCUMENTED_ERROR("This error is not documented. Check console for more information.")
}
