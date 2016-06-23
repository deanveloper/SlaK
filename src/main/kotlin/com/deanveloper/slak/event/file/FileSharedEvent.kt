package com.deanveloper.slak.event.file

import com.deanveloper.slak.message.SlaKFile
import java.time.LocalDateTime

class FileSharedEvent(file: SlaKFile, ts: LocalDateTime) : FileEvent {
    override val type = "file_shared"
    override val file = file
    override val ts = ts
}
