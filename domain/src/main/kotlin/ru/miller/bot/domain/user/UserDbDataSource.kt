package ru.miller.bot.domain.user

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.user.model.User
import ru.miller.bot.domain.user.model.params.CreateUpdateUserParams

interface UserDbDataSource {

    suspend fun createUser(params: CreateUpdateUserParams): User

    suspend fun getUserById(id: Id<User>): User
    suspend fun getUsers(): List<User>
    suspend fun getUserByTelegramId(id: Int): User

    suspend fun updateUser(params: CreateUpdateUserParams): User

    suspend fun deleteUser(id: Id<User>)
}
