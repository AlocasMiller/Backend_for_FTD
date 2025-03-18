package ru.bezdar.skip.app.api.user.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toResponse
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.domain.user.model.UserRole

@Serializable
data class UserResponse(
    val id: IdDto,
    val name: String,
    val login: String,
    val role: UserRole,
)

fun User.toResponse() = UserResponse(
    id = id.toResponse(),
    name = name,
    login = login,
    role = role,
)
