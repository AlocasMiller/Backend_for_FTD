package ru.miller.bot.app.api.user.model.response

import kotlinx.serialization.Serializable
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toResponse
import ru.miller.bot.domain.user.model.User

@Serializable
class UserResponse(
    val id: IdDto,
    val telegramId: Int,
    val name: String,
    val phone: String,
    val rate: Int,
    val studyPlanId: IdDto?,
)

fun User.toResponse() = UserResponse(
    id = id.toResponse(),
    telegramId = telegramId,
    name = name,
    phone = phone,
    rate = rate,
    studyPlanId = studyPlanId?.toResponse(),
)
