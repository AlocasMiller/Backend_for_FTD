package ru.bezdar.skip.domain.user.model.params

import ru.bezdar.skip.domain.user.model.UserRole
import java.util.UUID

data class UpdateRole(
    val id: UUID,
    val role: UserRole,
)
