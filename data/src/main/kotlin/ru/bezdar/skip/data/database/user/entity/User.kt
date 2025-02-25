package ru.bezdar.skip.data.database.user.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User
import java.util.UUID

object UserTable : UUIDTable("users") {

    val name = varchar("name", 30)
    val login = varchar("login", 30)
    val password = varchar("password", 255)
    val role = enumerationByName<UserRoleDb>("role", 20)
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    val name by UserTable.name
    val login by UserTable.login
    val password by UserTable.password
    val role by UserTable.role

    companion object : UUIDEntityClass<UserEntity>(UserTable)
}

fun UserEntity.toDomain() = User(
    id = Id(id.value),
    name = name,
    login = login,
    password = password,
    role = role.toDomain(),
)
