package ru.bezdar.skip.app.api.request.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toDomain
import ru.bezdar.skip.app.common.serializers.InstantAsDateTimeStringSerializer
import ru.bezdar.skip.domain.request.model.RequestReason
import ru.bezdar.skip.domain.request.model.RequestStatus
import ru.bezdar.skip.domain.request.model.params.NewRequest
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.app.common.validation.Validated
import ru.bezdar.skip.app.common.validation.isNotBlankAndEmpty
import java.time.Instant

@Serializable
data class CreateRequestBody(
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateStart: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateEnd: Instant,
    val comment: String? = null,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean,
    val files: List<ByteArray>,
) : Validated<CreateRequestBody> {
    override val validationRule: Validation<CreateRequestBody> = Validation {
        CreateRequestBody::comment ifPresent {
            isNotBlankAndEmpty()
            maxLength(255)
        }
    }
}

fun CreateRequestBody.toDomain(userId: IdDto) = NewRequest(
    creatorId = userId.toDomain<User>().value,
    dateStart = dateStart,
    dateEnd = dateEnd,
    comment = comment,
    status = status,
    reason = reason,
    fileInDean = fileInDean,
    files = files,
)
