package ru.miller.bot.app.api.lesson.model.response

import kotlinx.serialization.Serializable
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toResponse
import ru.miller.bot.app.common.serializers.InstantAsDateTimeStringSerializer
import ru.miller.bot.domain.lesson.model.Lesson
import java.time.Instant

@Serializable
class LessonResponse(
    val id: IdDto,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val startTime: Instant,
    @Serializable(with = InstantAsDateTimeStringSerializer::class)
    val endTime: Instant,
    val conferenceLink: String?,
    val studentId: IdDto,
)

fun Lesson.toResponse() = LessonResponse(
    id = id.toResponse(),
    startTime = startTime,
    endTime = endTime,
    conferenceLink = conferenceLink,
    studentId = studentId.toResponse()
)
