package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User
import ru.miller.bot.domain.user.model.params.CreateUpdateUserParams

interface CreateUserUseCase : UseCase<CreateUpdateUserParams, User>

class CreateUserUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : CreateUserUseCase {
    override suspend fun execute(param: CreateUpdateUserParams): User {
        return userDbDataSource.createUser(param)
    }
}
