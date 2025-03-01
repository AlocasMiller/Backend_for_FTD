package ru.bezdar.skip.domain.user.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.user.UserDbDataSource
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.domain.user.model.params.UpdateRole

interface UpdateUserRoleUseCase : UseCase<UpdateRole, User>

class UpdateUserRoleUseCaseImpl(
    private val userDbDataSource: UserDbDataSource
) : UpdateUserRoleUseCase {
    override suspend fun execute(param: UpdateRole): User {
        return userDbDataSource.updateUser(param)
    }
}
