package ru.bezdar.skip.app.api.user.model.body

import kotlinx.serialization.Serializable
import ru.bezdar.skip.domain.user.model.UserRole

@Serializable
data class UpdateRoleBody(
    val role: UserRole,
)
