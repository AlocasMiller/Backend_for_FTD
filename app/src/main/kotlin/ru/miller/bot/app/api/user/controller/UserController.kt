package ru.miller.bot.app.api.user.controller

import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toDomain
import ru.miller.bot.app.api.user.model.body.CreateUpdateUserBody
import ru.miller.bot.app.api.user.model.body.toDomain
import ru.miller.bot.app.api.user.model.response.UserResponse
import ru.miller.bot.app.api.user.model.response.toResponse
import ru.miller.bot.domain.user.model.User
import ru.miller.bot.domain.user.usecase.CreateUserUseCase
import ru.miller.bot.domain.user.usecase.DeleteUserByIdUseCase
import ru.miller.bot.domain.user.usecase.GetUserByIdUseCase
import ru.miller.bot.domain.user.usecase.GetUserByTelegramIdUseCase
import ru.miller.bot.domain.user.usecase.GetUsersUseCase
import ru.miller.bot.domain.user.usecase.UpdateUserByIdUseCase
import kotlin.getOrThrow

class UserController (
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getUserByTelegramIdUseCase: GetUserByTelegramIdUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
) {
    suspend fun createUser(body: CreateUpdateUserBody): UserResponse {
        val params = body.toDomain(null)
        return createUserUseCase(params).getOrThrow().toResponse()
    }

    suspend fun getUsers(): List<UserResponse> {
        return getUsersUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun getUserById(id: IdDto): UserResponse {
        return getUserByIdUseCase(id.toDomain<User>()).getOrThrow().toResponse()
    }

    suspend fun getUserByTelegramId(telegramId: Int): UserResponse {
        return getUserByTelegramIdUseCase(telegramId).getOrThrow().toResponse()
    }

    suspend fun updateUserById(id: IdDto, body: CreateUpdateUserBody): UserResponse {
        return updateUserByIdUseCase(body.toDomain(id)).getOrThrow().toResponse()
    }

    suspend fun deleteUserById(id: IdDto) {
        deleteUserByIdUseCase(id.toDomain<User>()).getOrThrow()
    }
}
