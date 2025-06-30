package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User

interface DeleteUserByIdUseCase : UseCase<Id<User>, Unit>

class DeleteUserByIdUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : DeleteUserByIdUseCase {
    override suspend fun execute(param: Id<User>) {
        userDbDataSource.deleteUser(param)
    }
}
