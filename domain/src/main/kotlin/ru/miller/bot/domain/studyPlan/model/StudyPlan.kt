package ru.miller.bot.domain.studyPlan.model

import ru.miller.bot.domain.common.model.Id

class StudyPlan(
    val id: Id<StudyPlan>,
    val name: String,
    val link: String,
)
