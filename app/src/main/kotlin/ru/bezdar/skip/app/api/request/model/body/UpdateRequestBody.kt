package ru.bezdar.skip.app.api.request.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.common.serializers.InstantAsDateTimeStringSerializer
import ru.bezdar.skip.app.common.validation.Validated
import ru.bezdar.skip.app.common.validation.isNotBlankAndEmpty
import ru.bezdar.skip.domain.request.model.RequestReason
import ru.bezdar.skip.domain.request.model.RequestStatus
import java.time.Instant

@Serializable
data class UpdateRequestBody(
    val creatorId: IdDto,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateStart: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val dateEnd: Instant,
    val comment: String,
    val status: RequestStatus,
    val reason: RequestReason,
    val fileInDean: Boolean,
) : Validated<UpdateRequestBody> {
    override val validationRule: Validation<UpdateRequestBody> = Validation {
        UpdateRequestBody::comment ifPresent {
            isNotBlankAndEmpty()
            maxLength(255)
        }
    }
}
