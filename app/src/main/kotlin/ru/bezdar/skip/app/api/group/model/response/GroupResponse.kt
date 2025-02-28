package ru.bezdar.skip.app.api.group.model.response

import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toResponse
import ru.bezdar.skip.app.api.user.model.response.UserResponse
import ru.bezdar.skip.app.api.user.model.response.toResponse
import ru.bezdar.skip.domain.group.model.Group

data class GroupResponse(
    val id: IdDto,
    val number: Int,
    val students: List<UserResponse>
)

fun Group.toResponse(): GroupResponse = GroupResponse(
    id = id.toResponse(),
    number = number,
    students = students.map { it.toResponse() },
)
