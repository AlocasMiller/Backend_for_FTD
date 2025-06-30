package ru.miller.bot.domain.lesson.model

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.user.model.User
import java.time.Instant

class Lesson(
    val id: Id<Lesson>,
    val startTime: Instant,
    val endTime: Instant,
    val conferenceLink: String?,
    val studentId: Id<User>,
)
