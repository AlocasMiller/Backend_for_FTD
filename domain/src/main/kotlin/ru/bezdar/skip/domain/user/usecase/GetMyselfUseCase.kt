package ru.bezdar.skip.domain.user.usecase

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.user.UserDbDataSource
import ru.bezdar.skip.domain.user.model.User

interface GetMyselfUseCase : UseCase<Id<User>, User>

class GetMyselfUseCaseImpl(
    private val userDbDataSource: UserDbDataSource,
) : GetMyselfUseCase {

    override suspend fun execute(userId: Id<User>): User {
        return userDbDataSource.getMyself(userId)
    }
}
