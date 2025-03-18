package ru.bezdar.skip.data.database.group.user.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.bezdar.skip.data.database.group.entity.GroupEntity
import ru.bezdar.skip.data.database.group.entity.GroupTable
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.data.database.user.entity.UserTable
import java.util.UUID

object GroupUserTable : UUIDTable("group_students") {

    val studentId = reference("student_id", UserTable)
    val groupId = reference("group_id", GroupTable)
}

class GroupUserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var student by UserEntity referencedOn GroupUserTable.studentId
    var group by GroupEntity referencedOn GroupUserTable.groupId

    companion object : UUIDEntityClass<GroupUserEntity>(GroupUserTable)
}
