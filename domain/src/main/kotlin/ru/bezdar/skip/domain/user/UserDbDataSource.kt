package ru.bezdar.skip.domain.user

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.domain.user.model.params.UpdateRole

interface UserDbDataSource {

    suspend fun getUsers(): List<User>
    suspend fun getMyself(userId: Id<User>): User

    suspend fun updateUser(params: UpdateRole): User
}
