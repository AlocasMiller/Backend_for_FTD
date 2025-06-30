package ru.miller.bot.domain.lesson.model.params

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.user.model.User
import java.time.Instant

class CreateUpdateLessonParams(
    val id: Id<Lesson>?,
    val studentId: Id<User>,
    val startTime: Instant,
    val endTime: Instant,
    val conferenceLink: String?,
)
