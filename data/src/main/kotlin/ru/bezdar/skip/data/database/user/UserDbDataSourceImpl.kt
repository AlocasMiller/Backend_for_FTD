package ru.bezdar.skip.data.database.user

import org.jetbrains.exposed.sql.Database
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.data.database.user.entity.toDb
import ru.bezdar.skip.data.database.user.entity.toDomain
import ru.bezdar.skip.domain.common.error.UserNotFound
import ru.bezdar.skip.domain.user.UserDbDataSource
import ru.bezdar.skip.domain.user.model.User
import ru.bezdar.skip.domain.user.model.params.UpdateRole

class UserDbDataSourceImpl(override val database: Database) : UserDbDataSource, DatabaseDataSourse {
    override suspend fun getUsers(): List<User> = dbQuery {
        UserEntity.all().map { it.toDomain() }
    }

    override suspend fun updateUser(params: UpdateRole): User = dbQuery {
        UserEntity.findById(params.id)?.apply {
            this.role = params.role.toDb()
        }?.toDomain() ?: throw UserNotFound()
    }
}
