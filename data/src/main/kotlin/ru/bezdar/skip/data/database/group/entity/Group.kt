package ru.bezdar.skip.data.database.group.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.bezdar.skip.data.database.group.user.entity.GroupUserTable
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.data.database.user.entity.toDomain
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.group.model.Group
import java.util.UUID

object GroupTable : UUIDTable("groups") {

    val number = integer("number").uniqueIndex()
}

class GroupEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var number by GroupTable.number

    var students by UserEntity via GroupUserTable

    companion object : UUIDEntityClass<GroupEntity>(GroupTable)
}

fun GroupEntity.toDomain() = Group(
    id = Id(id.value),
    number = number,
    students = students.map { it.toDomain() },
)
