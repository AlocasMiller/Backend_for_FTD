package ru.bezdar.skip.domain.request.model

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.user.model.User
import java.time.Instant

data class Request(
    val id: Id<Request>,
    val creator: User,
    val moderator: User?,
    val createdAt: Instant,
    val dateStart: Instant,
    val dateEnd: Instant,
    val comment: String?,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean = false,
    val files: List<File>,
)
