package ru.miller.bot.domain.user.model.params

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.studyPlan.model.StudyPlan
import ru.miller.bot.domain.user.model.User

class CreateUpdateUserParams(
    val id: Id<User>?,
    val telegramId: Int,
    val name: String,
    val phone: String,
    val rate: Int,
    val studyPlanId: Id<StudyPlan>?,
)
