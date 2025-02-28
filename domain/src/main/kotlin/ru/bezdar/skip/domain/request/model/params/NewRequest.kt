package ru.bezdar.skip.domain.request.model.params

import ru.bezdar.skip.domain.request.model.RequestReason
import ru.bezdar.skip.domain.request.model.RequestStatus
import java.time.Instant
import java.util.UUID

data class NewRequest(
    val creatorId: UUID,
    val dateStart: Instant,
    val dateEnd: Instant,
    val comment: String? = null,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean = false,
    val files: List<ByteArray>,
)
