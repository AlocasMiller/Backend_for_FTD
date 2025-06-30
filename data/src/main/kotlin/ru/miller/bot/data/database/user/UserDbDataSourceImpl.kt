package ru.miller.bot.data.database.user

import org.jetbrains.exposed.sql.Database
import ru.miller.bot.data.database.common.DatabaseDataSourse
import ru.miller.bot.data.database.studyPlan.entity.StudyPlanEntity
import ru.miller.bot.data.database.user.entity.UserEntity
import ru.miller.bot.data.database.user.entity.UserTable
import ru.miller.bot.data.database.user.entity.toDomain
import ru.miller.bot.domain.common.error.StudyPlanNotFound
import ru.miller.bot.domain.common.error.UserNotFound
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.user.UserDbDataSource
import ru.miller.bot.domain.user.model.User
import ru.miller.bot.domain.user.model.params.CreateUpdateUserParams

class UserDbDataSourceImpl(override val database: Database) : UserDbDataSource, DatabaseDataSourse {

    override suspend fun createUser(params: CreateUpdateUserParams): User = dbQuery {
        UserEntity.new {
            telegramId = params.telegramId
            name = params.name
            phone = params.phone
            rate = params.rate
        }.toDomain()
    }

    override suspend fun getUserById(id: Id<User>): User = dbQuery {
        UserEntity.findById(id.value)?.toDomain() ?: throw UserNotFound()
    }

    override suspend fun getUsers(): List<User> = dbQuery {
        UserEntity.all().map { it.toDomain() }
    }

    override suspend fun getUserByTelegramId(id: Int): User = dbQuery {
        UserEntity.find { UserTable.telegram_id eq id }.firstOrNull()?.toDomain() ?: throw UserNotFound()
    }

    override suspend fun updateUser(params: CreateUpdateUserParams): User = dbQuery {
        if (params.studyPlanId != null) {
            UserEntity.findById(params.id?.value ?: throw UserNotFound())?.apply {
                telegramId = params.telegramId
                name = params.name
                phone = params.phone
                rate = params.rate
                studyPlan = StudyPlanEntity.findById(params.studyPlanId?.value ?: throw StudyPlanNotFound())
            }?.toDomain() ?: throw UserNotFound()
        } else {
            UserEntity.findById(params.id?.value ?: throw UserNotFound())?.apply {
                telegramId = params.telegramId
                name = params.name
                phone = params.phone
                rate = params.rate
            }?.toDomain() ?: throw UserNotFound()
        }
    }

    override suspend fun deleteUser(id: Id<User>) = dbQueryWithoutResult {
        UserEntity.findById(id.value)?.delete() ?: throw UserNotFound()
    }
}
