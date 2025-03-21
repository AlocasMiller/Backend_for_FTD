package ru.bezdar.skip.app.api.common.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ru.bezdar.skip.domain.common.model.Id
import java.util.UUID

@Serializable
@JvmInline
value class IdDto(@Contextual val value: UUID)

internal fun <T> IdDto.toDomain(): Id<T> = Id(value)

internal fun <T> Id<T>.toResponse(): IdDto = IdDto(value)
