package ru.bezdar.skip.domain.group.model

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User

data class Group(
    val id: Id<Group>,
    val number: Int,
    val students: List<User>,
)
