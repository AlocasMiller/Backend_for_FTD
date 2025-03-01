package ru.bezdar.skip.domain.user.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.user.UserDbDataSource
import ru.bezdar.skip.domain.user.model.User

interface GetUserUseCase : UseCase<Unit, List<User>>

class GetUserUseCaseImpl(
    private val userDbDataSource: UserDbDataSource
) : GetUserUseCase {

    override suspend fun execute(param: Unit): List<User> {
        return userDbDataSource.getUsers()
    }
}