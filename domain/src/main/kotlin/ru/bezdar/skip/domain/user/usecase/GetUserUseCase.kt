package ru.bezdar.skip.domain.user.usecase

import ru.bezdar.skip.domain.common.usecase.UseCaseWithoutParams
import ru.bezdar.skip.domain.user.UserDbDataSource
import ru.bezdar.skip.domain.user.model.User

interface GetUserUseCase : UseCaseWithoutParams<List<User>>

class GetUserUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : GetUserUseCase {

    override suspend fun execute(): List<User> {
        return userDbDataSource.getUsers()
    }
}