package ru.bezdar.skip.domain.request.model.params

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.RequestReason
import ru.bezdar.skip.domain.request.model.RequestStatus
import ru.bezdar.skip.domain.user.model.User
import java.time.Instant

data class UpdateRequest(
    val id: Id<Request>,
    val creatorId: Id<User>,
    val moderatorId: Id<User>,
    val dateStart: Instant,
    val dateEnd: Instant,
    val comment: String,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean,
)
