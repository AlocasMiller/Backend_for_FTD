package ru.bezdar.skip.domain.user.model

import ru.bezdar.skip.domain.common.model.Id

data class User(
    val id: Id<User>,
    val name: String,
    val login: String,
    val password: String,
    val role: UserRole,
)
