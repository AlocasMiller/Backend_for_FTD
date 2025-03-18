package ru.bezdar.skip.app.api.user.controller

import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toDomain
import ru.bezdar.skip.app.api.user.model.body.UpdateRoleBody
import ru.bezdar.skip.app.api.user.model.response.UserResponse
import ru.bezdar.skip.app.api.user.model.response.toResponse
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.domain.user.model.params.UpdateRole
import ru.bezdar.skip.domain.user.usecase.GetMyselfUseCase
import ru.bezdar.skip.domain.user.usecase.UpdateUserRoleUseCase
import ru.bezdar.skip.domain.user.usecase.GetUserUseCase

class UserController (
    private val getUserUseCase: GetUserUseCase,
    private val getMyselfUseCase: GetMyselfUseCase,
    private val createUserUseCase: UpdateUserRoleUseCase,
) {
    suspend fun getUsers(): List<UserResponse> {
        return getUserUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun getMyself(userId: IdDto): UserResponse {
        return getMyselfUseCase(userId.toDomain<User>()).getOrThrow().toResponse()
    }

    suspend fun createUser(idDto: IdDto, body: UpdateRoleBody): UserResponse {
        val params = UpdateRole(idDto.value, body.role)
        return createUserUseCase(params).getOrThrow().toResponse()
    }
}
