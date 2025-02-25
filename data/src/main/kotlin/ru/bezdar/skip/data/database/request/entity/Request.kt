package ru.bezdar.skip.data.database.request.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.data.database.user.entity.UserTable
import ru.bezdar.skip.data.database.user.entity.toDomain
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request
import java.util.UUID

object RequestTable : UUIDTable("requests") {

    val dateStart = timestamp("deta_start")
    val dateEnd = timestamp("date_end").nullable().default(null)
    val comment = varchar("comment", 255).nullable().default(null)
    val status = enumerationByName<RequestStatusDb>("status", 20)

    val creatorId = reference("creator_id", UserTable.id)
    val moderatorId = reference("moderator_id", UserTable.id).nullable().default(null)
}

class RequestEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    val dateStart by RequestTable.dateStart
    val dateEnd by RequestTable.dateEnd
    val comment by RequestTable.comment
    val status by RequestTable.status

    val creator by UserEntity referencedOn RequestTable.creatorId
    val moderator by UserEntity optionalReferencedOn RequestTable.moderatorId
}

fun RequestEntity.toDomain() = Request(
    id = Id(id.value),
    creator = creator.toDomain(),
    moderator = moderator?.toDomain(),
    dateStart = dateStart,
    dateEnd = dateEnd,
    comment = comment,
    status = status.toDomain(),
)
