package ru.miller.bot.domain.user.model

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.studyPlan.model.StudyPlan

class User(
    val id: Id<User>,
    val telegramId: Int,
    val name: String,
    val phone: String,
    val rate: Int,
    val studyPlanId: Id<StudyPlan>?,
)
