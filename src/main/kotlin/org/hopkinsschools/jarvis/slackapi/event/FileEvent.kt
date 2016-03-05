package org.hopkinsschools.jarvis.slackapi.event

import org.hopkinsschools.jarvis.slackapi.Comment
import org.hopkinsschools.jarvis.slackapi.item.SlackFile
import java.time.Instant

sealed class FileEvent(val file: SlackFile, name: String, ts: Instant) : Event {
    override val name = name;
    override val ts = ts;

    class FileCreatedEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_created", ts);
    class FileSharedEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_shared", ts);
    class FileUnsharedEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_unshared", ts);
    class FilePublicizeEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_public", ts);
    class FilePrivatizeEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_private", ts);
    class FileChangeEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_change", ts);
    class FileDeletedEvent(file: SlackFile, ts: Instant) : FileEvent(file, "file_deleted", ts);

    sealed class FileCommentEvent(val comment: Comment, file: SlackFile, name: String, ts: Instant) :
            FileEvent(file, name, ts) {
        class FileCommentAddedEvent(comment: Comment, file: SlackFile, ts: Instant) :
                FileCommentEvent(comment, file, "file_comment_added", ts);
        class FileCommentEditedEvent(comment: Comment, file: SlackFile, ts: Instant) :
                FileCommentEvent(comment, file, "file_comment_edited", ts);
        class FileCommentDeletedEvent(comment: Comment, file: SlackFile, ts: Instant) :
                FileCommentEvent(comment, file, "file_comment_deleted", ts);
    }
}