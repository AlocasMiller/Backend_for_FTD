package ru.miller.bot.app.api.common.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID
import ru.miller.bot.domain.common.model.Id

@Serializable
@JvmInline
value class IdDto(@Contextual val value: UUID)

internal fun <T> IdDto.toDomain(): Id<T> = Id(value)

internal fun <T> Id<T>.toResponse(): IdDto = IdDto(value)
