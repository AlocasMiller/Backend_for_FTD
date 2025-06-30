package ru.miller.bot.app.api.user.model.body

import kotlinx.serialization.Serializable
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toDomain
import ru.miller.bot.domain.user.model.params.CreateUpdateUserParams

@Serializable
class CreateUpdateUserBody(
    val telegramId: Int,
    val name: String,
    val phone: String,
    val rate: Int,
    val studyPlanId: IdDto?,
)

fun CreateUpdateUserBody.toDomain(userId: IdDto?) = CreateUpdateUserParams(
    id = userId?.toDomain(),
    telegramId = telegramId,
    name = name,
    phone = phone,
    rate = rate,
    studyPlanId = studyPlanId?.toDomain(),
)
