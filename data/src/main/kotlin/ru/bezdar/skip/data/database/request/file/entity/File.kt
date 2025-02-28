package ru.bezdar.skip.data.database.request.file.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.bezdar.skip.data.database.request.entity.RequestEntity
import ru.bezdar.skip.data.database.request.entity.RequestTable
import ru.bezdar.skip.data.database.request.entity.toDomain
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.model.File
import java.util.UUID

object FileTable : UUIDTable("files") {

    val fileData = binary("file_data")

    val requestId = reference("request_id", RequestTable.id)
}

class FileEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var fileData by FileTable.fileData

    var request by RequestEntity referencedOn FileTable.requestId

    companion object : UUIDEntityClass<FileEntity>(FileTable)
}

fun FileEntity.toDomain() = File(
    id = Id(id.value),
    fileData = fileData,
)
