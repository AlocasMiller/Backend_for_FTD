package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User

interface GetUserByIdUseCase : UseCase<Id<User>, User>

class GetUserByIdUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : GetUserByIdUseCase {
    override suspend fun execute(param: Id<User>): User {
        return userDbDataSource.getUserById(param)
    }
}
