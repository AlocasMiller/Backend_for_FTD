package ru.bezdar.skip.domain.group.model.params

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User

data class NewGroup(
    val number: Int,
    val students: List<Id<User>>,
)
