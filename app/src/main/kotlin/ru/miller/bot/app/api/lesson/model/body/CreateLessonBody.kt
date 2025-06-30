package ru.miller.bot.app.api.lesson.model.body

import kotlinx.serialization.Serializable
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toDomain
import ru.miller.bot.app.common.serializers.InstantAsDateTimeStringSerializer
import ru.miller.bot.app.common.serializers.UUIDAsStringSerializer
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.params.CreateUpdateLessonParams
import ru.miller.bot.domain.user.model.User
import java.time.Instant
import java.util.UUID

@Serializable
class CreateLessonBody(
    @Serializable(with = UUIDAsStringSerializer::class)
    val studentId: UUID,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val startTime: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val endTime: Instant,
    val conferenceLink: String?,
)

fun CreateLessonBody.toDomain(lessonId: IdDto?) = CreateUpdateLessonParams(
    id = lessonId?.toDomain(),
    studentId = Id<User>(studentId),
    startTime = startTime,
    endTime = endTime,
    conferenceLink = conferenceLink
)
