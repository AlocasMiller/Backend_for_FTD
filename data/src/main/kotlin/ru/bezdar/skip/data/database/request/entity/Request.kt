package ru.bezdar.skip.data.database.request.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.bezdar.skip.data.database.request.file.entity.FileEntity
import ru.bezdar.skip.data.database.request.file.entity.FileTable
import ru.bezdar.skip.data.database.request.file.entity.toDomain
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.data.database.user.entity.UserTable
import ru.bezdar.skip.data.database.user.entity.toDomain
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request
import java.util.UUID

object RequestTable : UUIDTable("requests") {

    val createdAt = timestamp("created_at")
    val dateStart = timestamp("date_start")
    val dateEnd = timestamp("date_end")
    val comment = varchar("comment", 255).nullable().default(null)
    val status = enumerationByName<RequestStatusDb>("status", 20).default(RequestStatusDb.PENDING)
    val reason = enumerationByName<RequestReasonDb>("reason", 20)
    val fileInDean = bool("file_in_dean").default(false)

    val creatorId = reference("creator_id", UserTable.id)
    val moderatorId = reference("moderator_id", UserTable.id).nullable().default(null)
}

class RequestEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var createdAt by RequestTable.createdAt
    var dateStart by RequestTable.dateStart
    var dateEnd by RequestTable.dateEnd
    var comment by RequestTable.comment
    var status by RequestTable.status
    var reason by RequestTable.reason
    var fileInDean by RequestTable.fileInDean

    var creator by UserEntity referencedOn RequestTable.creatorId
    var moderator by UserEntity optionalReferencedOn RequestTable.moderatorId
    val files by FileEntity referrersOn FileTable.requestId

    companion object : UUIDEntityClass<RequestEntity>(RequestTable)
}

fun RequestEntity.toDomain() = Request(
    id = Id(id.value),
    creator = creator.toDomain(),
    moderator = moderator?.toDomain(),
    createdAt = createdAt,
    dateStart = dateStart,
    dateEnd = dateEnd,
    comment = comment,
    status = status.toDomain(),
    reason = reason.toDomain(),
    fileInDean = fileInDean,
    files = files.map(FileEntity::toDomain),
)
