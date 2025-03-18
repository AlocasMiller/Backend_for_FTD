package ru.bezdar.skip.app.api.request.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toResponse
import ru.bezdar.skip.app.api.user.model.response.UserResponse
import ru.bezdar.skip.app.api.user.model.response.toResponse
import ru.bezdar.skip.app.common.serializers.InstantAsDateTimeStringSerializer
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.RequestReason
import ru.bezdar.skip.domain.request.model.RequestStatus
import java.time.Instant

@Serializable
data class RequestResponse(
    val id: IdDto,
    val creator: UserResponse,
    val moderator: UserResponse?,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val createdAt: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateStart: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateEnd: Instant,
    val comment: String?,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean,
    val files: List<FileResponse>
)

fun Request.toResponse() = RequestResponse(
    id = id.toResponse(),
    creator = creator.toResponse(),
    moderator = moderator?.toResponse(),
    createdAt = createdAt,
    dateStart = dateStart,
    dateEnd = dateEnd,
    comment = comment,
    status = status,
    reason = reason,
    fileInDean = fileInDean,
    files = files.map { it.toResponse() },
)
