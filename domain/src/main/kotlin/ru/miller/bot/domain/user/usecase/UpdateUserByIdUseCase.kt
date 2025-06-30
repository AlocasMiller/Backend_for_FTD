package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User
import ru.miller.bot.domain.user.model.params.CreateUpdateUserParams

interface UpdateUserByIdUseCase : UseCase<CreateUpdateUserParams, User>

class UpdateUserByIdUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : UpdateUserByIdUseCase {
    override suspend fun execute(param: CreateUpdateUserParams): User {
        return userDbDataSource.updateUser(param)
    }
}
