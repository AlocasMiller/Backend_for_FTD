package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User

interface GetUserByTelegramIdUseCase : UseCase<Int, User>

class GetUserByTelegramIdUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : GetUserByTelegramIdUseCase {
    override suspend fun execute(param: Int): User {
        return userDbDataSource.getUserByTelegramId(param)
    }
}
