package ru.bezdar.skip.app.api.group.controller

import ru.bezdar.skip.app.api.group.model.body.CreateGroupBody
import ru.bezdar.skip.app.api.group.model.body.toDomain
import ru.bezdar.skip.app.api.group.model.response.GroupResponse
import ru.bezdar.skip.app.api.group.model.response.toResponse
import ru.bezdar.skip.domain.group.usecase.CreateGroupUseCase

class GroupController(
    private val createGroupUseCase: CreateGroupUseCase,
) {
    suspend fun createGroup(body: CreateGroupBody): GroupResponse {
        val params = body.toDomain()
        return createGroupUseCase(params).getOrThrow().toResponse()
    }
}
