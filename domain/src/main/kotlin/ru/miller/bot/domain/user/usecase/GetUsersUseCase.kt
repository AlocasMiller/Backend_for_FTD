package ru.miller.bot.domain.user.usecase

import ru.miller.bot.domain.common.usecase.UseCaseWithoutParams
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User

interface GetUsersUseCase : UseCaseWithoutParams<List<User>>

class GetUsersUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : GetUsersUseCase {
    override suspend fun execute(): List<User> {
        return userDbDataSource.getUsers()
    }
}
