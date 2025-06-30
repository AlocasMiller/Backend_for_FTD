package ru.miller.bot.app.api.lesson

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.miller.bot.app.common.serializers.InstantAsDateStringSerializer
import ru.miller.bot.app.common.serializers.UUIDAsStringSerializer
import java.time.Instant
import java.util.UUID

sealed class LessonRoute {

    @Serializable
    @Resource("/lessons")
    class Lessons(
        @Serializable(with = InstantAsDateStringSerializer::class)
        val date: Instant? = null,
    ) : LessonRoute()

    @Serializable
    @Resource("/lessons/{lessonsId}")
    class Lesson(
        @Serializable(with = UUIDAsStringSerializer::class)
        val lessonsId: UUID
    ) : LessonRoute()
}
